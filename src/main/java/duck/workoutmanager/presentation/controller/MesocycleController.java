package duck.workoutmanager.presentation.controller;

import duck.workoutmanager.application.command.mesocycle.CreateMesocycleCommand;
import duck.workoutmanager.application.command.mesocycle.UpdateMesocycleEndDateCommand;
import duck.workoutmanager.application.command.mesocycle.UpdateMesocycleNotesCommand;
import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.domain.model.Mesocycle;
import duck.workoutmanager.application.port.in.mesocycle.CreateMesocycleUseCase;
import duck.workoutmanager.application.port.in.mesocycle.UpdateMesocycleEndDateUseCase;
import duck.workoutmanager.application.port.in.mesocycle.UpdateMesocycleNotesUseCase;
import duck.workoutmanager.presentation.mapper.MesocyclePresentationMapper;
import duck.workoutmanager.presentation.request.mesocycle.CreateMesocycleRequest;
import duck.workoutmanager.presentation.request.mesocycle.UpdateMesocycleEndDateRequest;
import duck.workoutmanager.presentation.request.mesocycle.UpdateMesocycleNotesRequest;
import duck.workoutmanager.presentation.response.mesocycle.MesocycleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mesocycles")
@CrossOrigin("*")
public class MesocycleController {

    private final MesocyclePresentationMapper mesocycleMapper;

    private final CreateMesocycleUseCase createMesocycleUseCase;
    private final UpdateMesocycleNotesUseCase updateMesocycleNotesUseCase;
    private final UpdateMesocycleEndDateUseCase updateMesocycleEndDateUseCase;


    @PostMapping
    public ResponseEntity<MesocycleResponse> createMesocycle(
            @RequestBody CreateMesocycleRequest request
    ) {
        log.info("Start - create mesocycle for macrocycle: ({})", request.getMacrocycleId());

        CreateMesocycleCommand command = mesocycleMapper.toCommand(request);
        Mesocycle mesocycle = createMesocycleUseCase.create(command);

        log.info("End - create mesocycle for macrocycle: ({})", request.getMacrocycleId());

        return ResponseEntity.ok(mesocycleMapper.toResponse(mesocycle));
    }


    @PutMapping("/notes")
    public ResponseEntity<String> updateMesocycleNotes(
            @RequestBody UpdateMesocycleNotesRequest request
    ) {
        log.info("Start - update mesocycle notes for mesocycle: ({})", request.getMesocycleId());

        UpdateMesocycleNotesCommand command = mesocycleMapper.toCommand(request);
        String mesocycleNotes = updateMesocycleNotesUseCase.updateMesocycleNotes(command);

        log.info("End - update mesocycle notes for mesocycle: ({})", request.getMesocycleId());

        return ResponseEntity.ok(mesocycleNotes);
    }


    @PutMapping("/end-date")
    public ResponseEntity<String> updateMesocycleEndDate(
            @RequestBody UpdateMesocycleEndDateRequest request
    ) {
        log.info("Start - update mesocycle end date for mesocycle: ({})", request.getMesocycleId());

        UpdateMesocycleEndDateCommand command = mesocycleMapper.toCommand(request);
        LocalDate mesocycleEndDate = updateMesocycleEndDateUseCase.updateMesocycleEndDate(command);

        log.info("End - update mesocycle end date for mesocycle: ({})", request.getMesocycleId());

        return ResponseEntity.ok(mesocycleEndDate.toString());
    }

}
