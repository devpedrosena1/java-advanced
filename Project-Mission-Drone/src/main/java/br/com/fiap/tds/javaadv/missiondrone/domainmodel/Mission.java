package br.com.fiap.tds.javaadv.missiondrone.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "MISSION")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @Column(name = "DESCRIPTION", length = 100, nullable = false)
    private @Getter @Setter String description;

    @Column(name = "LOCATION", length = 50, nullable = false)
    private @Getter @Setter String location;

    @Column(name = "SCHEDULE_DATE", nullable = false)
    private @Getter @Setter LocalDate scheduledDate;

    @Column(name = "ESTIMATED_BATTERY_USAGE")
    private @Getter @Setter Integer estimatedBatteryUsage;

    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = false)
    private @Getter @Setter Drone droneId;

}
