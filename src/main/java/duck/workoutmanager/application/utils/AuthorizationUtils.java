package duck.workoutmanager.application.utils;

import duck.workoutmanager.application.domain.exception.AuthorizationException;
import duck.workoutmanager.configuration.security.CustomUserDetails;
import duck.workoutmanager.configuration.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationUtils {


    public String getCurrentUserEmail() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            log.error("Authentication object is null");
            throw new AuthorizationException("User is not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails customUserDetails) {
            return customUserDetails.getUsername();
        }

        log.error("Principal is not of type CustomUserDetails");
        throw new AuthorizationException("User is not authenticated");
    }
}
