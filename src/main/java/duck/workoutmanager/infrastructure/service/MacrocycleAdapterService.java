package duck.workoutmanager.infrastructure.service;

import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.port.out.macrocycle.CreateMacrocyclePortOut;
import duck.workoutmanager.infrastructure.entity.MacrocycleEntity;
import duck.workoutmanager.infrastructure.mapper.MacrocycleInfrastructureMapper;
import duck.workoutmanager.infrastructure.repository.MacrocycleJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MacrocycleAdapterService implements CreateMacrocyclePortOut {

    private final MacrocycleJpaRepository macrocycleJpaRepository;

    private final MacrocycleInfrastructureMapper macrocycleMapper;


    @Override
    public Macrocycle create(Macrocycle macrocycle) {
        log.info("Start - create macrocycle in database for user: ({})", macrocycle.getUser().getEmail());

        MacrocycleEntity savedEntity = macrocycleJpaRepository.save(
                macrocycleMapper.toEntityWithUserEmail(macrocycle)
        );

        log.info("End - create macrocycle in database for user: ({})", macrocycle.getUser().getEmail());

        return macrocycleMapper.toModelWithNoDependencies(savedEntity);
    }
}
