package br.com.fiap.tds.javaadv.missiondrone.service;

import br.com.fiap.tds.javaadv.missiondrone.domainmodel.Drone;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DroneService {

    public List<Drone> findAll();
    public Optional<Drone> findById(UUID id);
    public Drone create(Drone drone);
    public boolean existsById(UUID id);
    public void remove(Drone drone);

}
