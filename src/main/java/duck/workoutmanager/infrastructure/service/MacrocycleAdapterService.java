package duck.workoutmanager.infrastructure.service;

import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.port.out.macrocycle.CreateMacrocyclePortOut;
import duck.workoutmanager.application.port.out.macrocycle.GetMacrocyclePortOut;
import duck.workoutmanager.application.port.out.macrocycle.UpdateMacrocyclePortOut;
import duck.workoutmanager.infrastructure.entity.MacrocycleEntity;
import duck.workoutmanager.infrastructure.mapper.MacrocycleInfrastructureMapper;
import duck.workoutmanager.infrastructure.repository.MacrocycleJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MacrocycleAdapterService implements
        CreateMacrocyclePortOut,
        GetMacrocyclePortOut,
        UpdateMacrocyclePortOut {

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


    @Override
    public Macrocycle getByIdWithUser(UUID macrocycleId) {
        log.info("Start - get macrocycle from database by id: ({})", macrocycleId);

        Optional<MacrocycleEntity> macrocycle = macrocycleJpaRepository.findById(macrocycleId);

        log.info("End - get macrocycle from database by id: ({})", macrocycleId);

        return macrocycle.map(macrocycleMapper::toModelWithUser).orElse(null);
    }


    @Override
    public List<Macrocycle> getByUserEmail(String userEmail) {
        log.info("Start - get macrocycles from database by user email: ({})", userEmail);

        List<MacrocycleEntity> macrocycleEntities = macrocycleJpaRepository.findAllByUserEmail(userEmail);

        log.info("End - get macrocycles from database by user email: ({})", userEmail);

        return macrocycleEntities.stream()
                .map(macrocycleMapper::toModelWithUser)
                .toList();
    }


    @Override
    public Macrocycle update(Macrocycle macrocycle) {
        log.info("Start - update macrocycle in database with id: ({})", macrocycle.getId());

        MacrocycleEntity updatedEntity = macrocycleJpaRepository.save(
                macrocycleMapper.toEntityWithUserEmail(macrocycle)
        );

        log.info("End - update macrocycle in database with id: ({})", macrocycle.getId());

        return macrocycleMapper.toModelWithNoDependencies(updatedEntity);
    }


    @Override
    public List<Macrocycle> updateAll(List<Macrocycle> macrocycles) {
        log.info("Start - update multiple macrocycles in database");

        List<MacrocycleEntity> entitiesToUpdate = macrocycles.stream()
                .map(macrocycleMapper::toEntityWithUserEmail)
                .toList();

        List<MacrocycleEntity> updatedEntities = macrocycleJpaRepository.saveAll(entitiesToUpdate);

        log.info("End - update multiple macrocycles in database");

        return updatedEntities.stream()
                .map(macrocycleMapper::toModelWithNoDependencies)
                .toList();
    }
}
