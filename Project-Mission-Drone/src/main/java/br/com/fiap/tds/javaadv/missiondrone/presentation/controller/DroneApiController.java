package br.com.fiap.tds.javaadv.missiondrone.presentation.controller;

import br.com.fiap.tds.javaadv.missiondrone.domainmodel.Drone;
import br.com.fiap.tds.javaadv.missiondrone.presentation.transferObjects.DroneDTO;
import br.com.fiap.tds.javaadv.missiondrone.service.DroneService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/drone")
@Tag(name = "Drone", description = "Operações relacionadas a controle de drones.")
public class DroneApiController {

    private final DroneService droneService;

    // http://localhost:8080/console
    // http://localhost:8080/swagger-ui/index.html

    // cadastro
    @PostMapping
    public ResponseEntity<DroneDTO> save(@Valid @RequestBody DroneDTO droneDto) {
        Drone newDrone = this.droneService.create(DroneDTO.toEntity(droneDto));
        return new ResponseEntity<>(DroneDTO.fromEntity(newDrone), HttpStatus.CREATED);
    }
    
    // consulta
    
    // update

    // remocao

}
