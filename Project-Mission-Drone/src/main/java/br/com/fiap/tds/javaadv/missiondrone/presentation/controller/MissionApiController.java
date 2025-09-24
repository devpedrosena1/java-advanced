package br.com.fiap.tds.javaadv.missiondrone.presentation.controller;

import br.com.fiap.tds.javaadv.missiondrone.domainmodel.Mission;
import br.com.fiap.tds.javaadv.missiondrone.presentation.transferObjects.MissionDTO;
import br.com.fiap.tds.javaadv.missiondrone.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/mission")
@RequiredArgsConstructor
@Tag(name = "Mission", description = "Mission operations")
public class MissionApiController {

    private final MissionService missionService;

    @Operation(summary = "Insert mission")
    @PostMapping
    public ResponseEntity<MissionDTO> save(@Valid @RequestBody MissionDTO missionDto) {
        Mission newMission = this.missionService.create(MissionDTO.toEntity(missionDto));
        return new ResponseEntity<>(MissionDTO.fromEntity(newMission), HttpStatus.CREATED);
    }

    @Operation(summary = "Return mission by id")
    @GetMapping("/{id}")
    public ResponseEntity<MissionDTO> findById(@PathVariable("id") UUID id, @Valid @RequestBody MissionDTO missionDto) {
        return this.missionService.findById(id)
                .map(mission -> ResponseEntity.ok(MissionDTO.fromEntity(mission)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Return the complete mission object")
    @GetMapping
    public ResponseEntity<List<Mission>> findAll() {
        return ResponseEntity.ok(this.missionService.findAll());
    }

    @Operation(summary = "Returns all missions assigned to a specific drone")
    @GetMapping("/mission-drone/{droneId}")
    public List<MissionDTO> getMissionsByDrone(@PathVariable("droneId") UUID droneId) {
        return missionService.findByDroneId_Id(droneId)
                .stream()
                .map(MissionDTO::fromEntity)
                .toList();
    }
}