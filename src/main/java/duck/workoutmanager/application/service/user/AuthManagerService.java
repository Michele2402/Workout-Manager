package duck.workoutmanager.application.service.user;

import duck.workoutmanager.application.command.user.LoginCommand;
import duck.workoutmanager.application.command.user.RegisterCommand;
import duck.workoutmanager.application.domain.enums.RoleEnum;
import duck.workoutmanager.application.domain.exception.AuthorizationException;
import duck.workoutmanager.application.domain.exception.ObjectIsNullException;
import duck.workoutmanager.application.domain.model.User;
import duck.workoutmanager.application.port.in.user.LoginUseCase;
import duck.workoutmanager.application.port.in.user.RegisterUserCase;
import duck.workoutmanager.application.port.out.user.CreateUserPortOut;
import duck.workoutmanager.application.port.out.user.GetUserPortOut;
import duck.workoutmanager.application.utils.AuthorizationUtils;
import duck.workoutmanager.application.utils.CheckAttribute;
import duck.workoutmanager.configuration.security.CustomUserDetails;
import duck.workoutmanager.configuration.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthManagerService implements LoginUseCase, RegisterUserCase {

    @Value("${default.user.password}")
    private String defaultPassword;

    private final CheckAttribute checkAttribute;
    private final AuthorizationUtils authorizationUtils;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final GetUserPortOut getUserPortOut;
    private final CreateUserPortOut createUserPortOut;

    @Override
    public String login(LoginCommand command) {

        validateLoginCommand(command);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        command.getEmail(),
                        command.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            if(userDetails == null) {
                log.error("UserDetails is null after authentication");
                throw new ObjectIsNullException("UserDetails is null after authentication");
            }

            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("USER");

            return jwtService.generateToken(command.getEmail(), role);
        }

        log.error("User Login Failed");
        return "failure";
    }


    @Override
    public void register(RegisterCommand command) {
        validateRegistrationCommand(command);

        String trainerEmail = authorizationUtils.getCurrentUserEmail();

        User user = User.builder()
                .name(command.getName())
                .surname(command.getSurname())
                .email(command.getEmail())
                .password(bCryptPasswordEncoder.encode(defaultPassword))
                .role(RoleEnum.USER)
                .trainerEmail(trainerEmail)
                .build();

        User savedUser = getUserPortOut.getByEmail(command.getEmail());

        if(savedUser != null) {
            log.error("User with email {} already exists", command.getEmail());
            throw new AuthorizationException("User with email " + command.getEmail() + " already exists");
        }

        createUserPortOut.createUser(user);
    }



    private void validateLoginCommand(LoginCommand request) {

        if(request == null) {
            log.error("LoginRequest is null");
            throw new ObjectIsNullException("LoginRequest is null");
        }

        checkAttribute.checkStringIsNotNullOrEmpty(request.getEmail(), "Email");
        checkAttribute.checkStringIsNotNullOrEmpty(request.getPassword(), "Password");
    }

    private void validateRegistrationCommand(RegisterCommand request) {

        if(request == null) {
            log.error("RegisterRequest is null");
            throw new ObjectIsNullException("RegisterRequest is null");
        }

        checkAttribute.checkStringIsNotNullOrEmpty(request.getName(), "Name");
        checkAttribute.checkStringIsShorterThan(request.getName(), 255, "Name");
        checkAttribute.checkStringIsNotNullOrEmpty(request.getSurname(), "Surname");
        checkAttribute.checkStringIsShorterThan(request.getSurname(), 255, "Surname");
        checkAttribute.checkStringIsNotNullOrEmpty(request.getEmail(), "Email");
        checkAttribute.checkEmailFormat(request.getEmail(), "Email");
    }
}
