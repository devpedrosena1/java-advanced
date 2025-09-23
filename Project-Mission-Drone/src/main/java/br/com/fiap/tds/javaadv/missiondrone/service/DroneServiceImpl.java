package br.com.fiap.tds.javaadv.missiondrone.service;

import br.com.fiap.tds.javaadv.missiondrone.domainmodel.Drone;
import br.com.fiap.tds.javaadv.missiondrone.domainmodel.repositories.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    @Override
    public List<Drone> findAll() {
        return new ArrayList<>(
                this.droneRepository.findAll()
        );
    }

    @Override
    public Optional<Drone> findById(UUID id) {
        return this.droneRepository.findById(id);
    }

    @Override
    public Drone create(Drone drone) {
        return this.droneRepository.save(drone);
    }

    @Override
    public boolean existsById(UUID id) {
        return this.droneRepository.existsById(id);
    }

    @Override
    public Drone update(UUID id, Drone drone) {
        Drone droneFromDatabase = this.droneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Drone not found"));

        // exemplo: atualiza só se não for null
        if (drone.getModel() != null) {
            droneFromDatabase.setModel(drone.getModel());
        }
        if (droneFromDatabase.getBatteryCapacity() != drone.getBatteryCapacity()) {
            droneFromDatabase.setBatteryCapacity(drone.getBatteryCapacity());
        }
        if (drone.getStatus() != null) {
            droneFromDatabase.setStatus(drone.getStatus());
        }

        return this.droneRepository.save(droneFromDatabase);
    }

    @Override
    public void remove(Drone drone) {
        this.droneRepository.delete(drone);
    }

    @Override
    public void removeById(UUID id) {
        this.droneRepository.deleteById(id);
    }

    @Override
    public Double findAverageBatteryUsage(UUID droneId) {
        if (!droneRepository.existsById(droneId)) {
            throw new IllegalArgumentException("Drone not found");
        }
        Optional<Double> optionalAverage = this.droneRepository.findAverageBatteryUsage(droneId);
        return optionalAverage.orElse(0.0);
    }
}
