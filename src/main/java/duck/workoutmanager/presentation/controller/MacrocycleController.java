package duck.workoutmanager.presentation.controller;

import duck.workoutmanager.application.domain.model.Macrocycle;
import duck.workoutmanager.application.port.in.macrocycle.CreateMacrocycleUseCase;
import duck.workoutmanager.presentation.mapper.MacrocyclePresentationMapper;
import duck.workoutmanager.presentation.request.macrocycle.CreateMacrocycleRequest;
import duck.workoutmanager.presentation.response.macrocycle.MacrocycleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/macrocycles")
@CrossOrigin("*")
public class MacrocycleController {

    private final MacrocyclePresentationMapper macrocyclePresentationMapper;

    private final CreateMacrocycleUseCase createMacrocycleUseCase;

    @PostMapping
    public ResponseEntity<MacrocycleResponse> createMacrocycle(
            @RequestBody CreateMacrocycleRequest request
    ) {
        log.info("Start - create macrocycle for user: ({})", request.getUserEmail());

        Macrocycle macrocycle = createMacrocycleUseCase.createMacrocycle(
                macrocyclePresentationMapper.toCommand(request)
        );

        log.info("End - create macrocycle for user: ({})", request.getUserEmail());

        return ResponseEntity.ok(macrocyclePresentationMapper.toResponse(macrocycle));
    }

}
