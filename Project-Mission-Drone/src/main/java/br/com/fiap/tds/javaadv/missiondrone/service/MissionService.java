package br.com.fiap.tds.javaadv.missiondrone.service;

import br.com.fiap.tds.javaadv.missiondrone.domainmodel.Mission;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MissionService {

    public Mission create(Mission mission);
    public Optional<Mission> findById(UUID id);
    public List<Mission> findAll();
    List<Mission> findByDroneId_Id(UUID droneIdId);

}
