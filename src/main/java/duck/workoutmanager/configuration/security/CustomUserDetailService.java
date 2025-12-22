package duck.workoutmanager.configuration.security;

import duck.workoutmanager.application.domain.exception.ObjectNotFoundException;
import duck.workoutmanager.infrastructure.entity.UserEntity;
import duck.workoutmanager.infrastructure.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> user = userJpaRepository.findByEmail(username);

        if(user.isEmpty()) {
            log.error("User not found");
            throw new ObjectNotFoundException("User not found");
        }

        return new CustomUserDetails(user.get());
    }
}
