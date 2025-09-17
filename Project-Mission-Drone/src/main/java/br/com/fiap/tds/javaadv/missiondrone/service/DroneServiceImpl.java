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

    public Drone partialUpdate(UUID id, Drone drone) {
        if( !this.droneRepository.existsById(id) )
            throw new IllegalArgumentException("Entity not found");
        Drone droneFromDatabase = this.droneRepository.findById(id).orElse(null);

        if (!Objects.equals(droneFromDatabase.getBatteryCapacity(), drone.getBatteryCapacity())) {
            droneFromDatabase.setBatteryCapacity(drone.getBatteryCapacity());
        }


        return this.create(droneFromDatabase);
    }

    @Override
    public void remove(Drone drone) {

    }
}
