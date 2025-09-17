package br.com.fiap.tds.javaadv.missiondrone.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "DRONE")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @Column(name = "MODEL", length = 50, nullable = false)
    private @Getter @Setter String model;

    @Column(name = "BATTERY_CAPACITY", nullable = false)
    private @Getter @Setter int batteryCapacity;

    @Column(name = "STATUS", length = 50)
    private @Getter @Setter String status;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Drone drone = (Drone) o;
        return Objects.equals(id, drone.id);
    }

    public Drone(UUID id) {
        this.id = id;
    }

    public Drone(String model, int batteryCapacity, String status) {
        this.model = model;
        this.batteryCapacity = batteryCapacity;
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", modelo='" + model + '\'' +
                ", capacidadeBateria=" + batteryCapacity +
                ", status='" + status + '\'' +
                '}';
    }
}
