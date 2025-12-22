package duck.workoutmanager.application.service.user;

import duck.workoutmanager.application.command.user.LoginCommand;
import duck.workoutmanager.application.domain.exception.ObjectIsNullException;
import duck.workoutmanager.application.port.in.user.LoginUseCase;
import duck.workoutmanager.application.utils.CheckAttribute;
import duck.workoutmanager.configuration.security.CustomUserDetails;
import duck.workoutmanager.configuration.security.JwtService;
import duck.workoutmanager.presentation.request.user.LoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthManagerService implements LoginUseCase {

    private final CheckAttribute checkAttribute;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String login(LoginCommand command) {

        validateLoginRequest(command);

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




    private void validateLoginRequest(LoginCommand request) {

        if(request == null) {
            log.error("LoginRequest is null");
            throw new ObjectIsNullException("LoginRequest is null");
        }

        checkAttribute.checkStringIsNullOrEmpty(request.getEmail(), "Email");
        checkAttribute.checkStringIsNullOrEmpty(request.getPassword(), "Password");
    }
}
