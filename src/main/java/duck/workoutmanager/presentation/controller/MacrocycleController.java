package duck.workoutmanager.presentation.controller;

import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.port.in.macrocycle.ActivateMacrocycleUseCase;
import duck.workoutmanager.application.port.in.macrocycle.CreateMacrocycleUseCase;
import duck.workoutmanager.presentation.mapper.MacrocyclePresentationMapper;
import duck.workoutmanager.presentation.request.macrocycle.ActivateMacrocycleRequest;
import duck.workoutmanager.presentation.request.macrocycle.CreateMacrocycleRequest;
import duck.workoutmanager.presentation.response.macrocycle.MacrocycleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/macrocycles")
@CrossOrigin("*")
public class MacrocycleController {

    private final MacrocyclePresentationMapper macrocycleMapper;

    private final CreateMacrocycleUseCase createMacrocycleUseCase;
    private final ActivateMacrocycleUseCase activateMacrocycleUseCase;

    @PostMapping
    public ResponseEntity<MacrocycleResponse> createMacrocycle(
            @RequestBody CreateMacrocycleRequest request
    ) {
        log.info("Start - create macrocycle for user: ({})", request.getUserEmail());

        Macrocycle macrocycle = createMacrocycleUseCase.createMacrocycle(
                macrocycleMapper.toCommand(request)
        );

        log.info("End - create macrocycle for user: ({})", request.getUserEmail());

        return ResponseEntity.ok(macrocycleMapper.toResponse(macrocycle));
    }


    @PutMapping("/activate")
    public ResponseEntity<List<MacrocycleResponse>> activateMacrocycle(
            @RequestBody ActivateMacrocycleRequest request
    ) {
        log.info("Start - activate macrocycle with id: ({})", request.getMacrocycleId());

        List<Macrocycle> userMacrocycles = activateMacrocycleUseCase.activateMacrocycle(
                macrocycleMapper.toCommand(request)
        );
        List<MacrocycleResponse> response = userMacrocycles.stream()
                .map(macrocycleMapper::toResponse)
                .toList();

        log.info("End - activate macrocycle with id: ({})", request.getMacrocycleId());

        return ResponseEntity.ok(response);
    }

}
