package br.com.fiap.tds.javaadv.missiondrone.presentation.transferObjects;

import br.com.fiap.tds.javaadv.missiondrone.domainmodel.Mission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter
@Builder
public class MissionDTO {

    private UUID id;

    @NotBlank(message = "This field is required.")
    @Size(max=100, message = "The field must have a maximum of 100 characters")
    private String description;

    @NotBlank(message = "This field is required.")
    private String location;

    @NotBlank(message = "This field is required.")
    private LocalDate scheduledDate;

    @NotBlank(message = "This field is required.")
    private UUID droneId;

    public static MissionDTO fromEntity(Mission mission) {
        if (mission == null) return null;
        return MissionDTO.builder()
                .id(mission.getId())
                .description(mission.getDescription())
                .location(mission.getLocation())
                .scheduledDate(mission.getScheduledDate())
                .droneId(mission.getDroneId().getId())
                .build();
    }

    public static Mission toEntity(MissionDTO dto) {
        if (dto == null) return null;
        return Mission.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .scheduledDate(dto.getScheduledDate())
                .build();
    }


}
