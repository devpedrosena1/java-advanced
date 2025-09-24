package br.com.fiap.tds.javaadv.missiondrone.presentation.transferObjects;

import br.com.fiap.tds.javaadv.missiondrone.domainmodel.Drone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneDTO {

    private UUID id;

    @NotBlank(message = "This field is required.")
    private String model;

    @NotNull(message = "This field is required.")
    private int batteryCapacity;

    @NotBlank(message = "This field is required.")
    private String status;

    public static DroneDTO fromEntity(Drone drone) {
        if (drone == null) return null;
        return DroneDTO.builder()
                .id(drone.getId())
                .model(drone.getModel())
                .batteryCapacity(drone.getBatteryCapacity())
                .status(drone.getStatus())
                .build();
    }

    public static Drone toEntity(DroneDTO dto) {
        if (dto == null) return null;
        return Drone.builder()
                .id(dto.getId())
                .model(dto.getModel())
                .batteryCapacity(dto.getBatteryCapacity())
                .status(dto.getStatus())
                .build();
    }

}
