package duck.workoutmanager.infrastructure.service;

import duck.workoutmanager.application.domain.model.User;
import duck.workoutmanager.application.port.out.user.CheckUserPortOut;
import duck.workoutmanager.application.port.out.user.CreateUserPortOut;
import duck.workoutmanager.application.port.out.user.GetUserPortOut;
import duck.workoutmanager.infrastructure.entity.UserEntity;
import duck.workoutmanager.infrastructure.mapper.UserInfrastructureMapper;
import duck.workoutmanager.infrastructure.repository.UserJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAdapterService implements
        GetUserPortOut,
        CreateUserPortOut,
        CheckUserPortOut
{

    private final UserJpaRepository userJpaRepository;

    private final UserInfrastructureMapper userMapper;

    @Override
    @Transactional
    public User getByEmail(String email) {
        log.info("Start - Get user by email: ({})", email);

        Optional<UserEntity> optionalUser = userJpaRepository.findByEmail(email);

        log.info("End - Get user by email: ({})", email);

        return optionalUser.map(userMapper::toModel).orElse(null);
    }

    @Override
    @Transactional
    public void createUser(User user) {
        log.info("Start - Create user: ({})", user.getEmail());

        userJpaRepository.save(userMapper.toEntity(user));

        log.info("End - Create user: ({})", user.getEmail());
    }

    @Override
    public boolean checkUserHasAnyMacrocycle(String userEmail) {
        log.info("Start - Check user has any macrocycle: ({})", userEmail);

        boolean hasAnyMacrocycle = userJpaRepository.hasAnyMacrocycle(userEmail);

        log.info("End - Check user has any macrocycle: ({})", userEmail);

        return hasAnyMacrocycle;
    }
}