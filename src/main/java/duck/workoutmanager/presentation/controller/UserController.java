package duck.workoutmanager.presentation.controller;

import duck.workoutmanager.application.port.in.user.LoginUseCase;
import duck.workoutmanager.application.port.in.user.RegisterUserCase;
import duck.workoutmanager.presentation.mapper.UserPresentationMapper;
import duck.workoutmanager.presentation.request.user.LoginRequest;
import duck.workoutmanager.presentation.request.user.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final LoginUseCase loginUseCase;
    private final RegisterUserCase registerUserCase;

    private final UserPresentationMapper userMapper;

    @PostMapping("/register")
    public void register(
            @RequestBody RegisterRequest request
    ) {
        log.info("Start - register user: ({})", request.getEmail());

        registerUserCase.register(userMapper.toCommand(request));

        log.info("End - register user: ({})", request.getEmail());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequest request
    ) {
        log.info("Start - login user: ({})", request.getEmail());

        String token = loginUseCase.login(userMapper.toCommand(request));

        log.info("End - login user: ({})", request.getEmail());

        return ResponseEntity.ok(token);
    }
}
