package br.com.fiap.tds.javaadv.missiondrone.presentation.controller;

import br.com.fiap.tds.javaadv.missiondrone.domainmodel.Drone;
import br.com.fiap.tds.javaadv.missiondrone.presentation.transferObjects.DroneDTO;
import br.com.fiap.tds.javaadv.missiondrone.service.DroneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/drone")
@Tag(name = "Drone", description = "Drone operations")
public class DroneApiController {

    private final DroneService droneService;

    // http://localhost:8080/console
    // http://localhost:8080/swagger-ui/index.html

    @Operation(summary = "Insert drone")
    @PostMapping
    public ResponseEntity<DroneDTO> save(@Valid @RequestBody DroneDTO droneDto) {
        Drone newDrone = this.droneService.create(DroneDTO.toEntity(droneDto));
        return new ResponseEntity<>(DroneDTO.fromEntity(newDrone), HttpStatus.CREATED);
    }

    @Operation(summary = "Return drone by id")
    @GetMapping("/{id}")
    public ResponseEntity<DroneDTO> findById(@PathVariable("id") UUID id) {
        return this.droneService.findById(id)
                .map(drone -> ResponseEntity.ok(DroneDTO.fromEntity(drone)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update the complete drone object")
    @PutMapping("/{id}")
    public ResponseEntity<DroneDTO> update(@PathVariable("id") UUID id, @Valid @RequestBody DroneDTO droneDto) {
        if (!this.droneService.existsById(id))
            return ResponseEntity.notFound().build();
        Drone drone = DroneDTO.toEntity(droneDto);
        drone.setId(id);
        return new ResponseEntity<>(
                DroneDTO.fromEntity(this.droneService.create(drone)),
                HttpStatus.CREATED
        );
    }

    // att parcialmente

    @Operation(summary = "Delete drone by id")
    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestBody UUID id) {
        if (!this.droneService.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone not found.");
        this.droneService.removeById(id);
        return ResponseEntity.noContent().build();
    }

    // delete all

}
