package duck.workoutmanager.infrastructure.service;

import duck.workoutmanager.application.domain.model.Mesocycle;
import duck.workoutmanager.application.port.out.mesocycle.CreateMesocyclePortOut;
import duck.workoutmanager.application.port.out.mesocycle.GetMesocyclePortOut;
import duck.workoutmanager.application.port.out.mesocycle.UpdateMesocyclePortOut;
import duck.workoutmanager.infrastructure.entity.MesocycleEntity;
import duck.workoutmanager.infrastructure.mapper.MesocycleInfrastructureMapper;
import duck.workoutmanager.infrastructure.repository.MesocycleJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MesocycleAdapterService implements
        CreateMesocyclePortOut,
        GetMesocyclePortOut,
        UpdateMesocyclePortOut
{

    private final MesocycleInfrastructureMapper mesocycleMapper;

    private final MesocycleJpaRepository mesocycleJpaRepository;


    @Override
    @Transactional
    public Mesocycle create(Mesocycle mesocycle) {
        log.info("Start - create macrocycle in database for macrocycle: ({})", mesocycle.getMacrocycle().getId());

        MesocycleEntity mesocycleToSave = mesocycleMapper.toEntityWithWeeksAndMacrocycleId(mesocycle);
        MesocycleEntity savedEntity = mesocycleJpaRepository.save(mesocycleToSave);

        log.info("End - create macrocycle in database for macrocycle: ({})", mesocycle.getMacrocycle().getId());

        return mesocycleMapper.toModel(savedEntity);
    }


    @Override
    @Transactional
    public Mesocycle getByIdWithUser(UUID mesocycleId) {
        log.info("Start - get mesocycle by id ({}) with user from database", mesocycleId);

        Optional<MesocycleEntity> mesocycleEntity = mesocycleJpaRepository.findByIdWithUser(mesocycleId);

        log.info("End - get mesocycle by id ({}) with user from database", mesocycleId);

        return mesocycleEntity.map(mesocycleMapper::toModelWithUser).orElse(null);
    }


    @Override
    @Transactional
    public Mesocycle update(Mesocycle mesocycle) {
        log.info("Start - update mesocycle in database for mesocycle: ({})", mesocycle.getId());

        MesocycleEntity mesocycleToUpdate = mesocycleMapper.toEntityWithMacrocycleId(mesocycle);
        MesocycleEntity updatedEntity = mesocycleJpaRepository.save(mesocycleToUpdate);

        log.info("End - update mesocycle in database for mesocycle: ({})", mesocycle.getId());

        return mesocycleMapper.toModel(updatedEntity);
    }
}
