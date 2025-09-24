package br.com.fiap.tds.javaadv.missiondrone.service;

import br.com.fiap.tds.javaadv.missiondrone.domainmodel.Mission;
import br.com.fiap.tds.javaadv.missiondrone.domainmodel.repositories.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService{

    private final MissionRepository missionRepository;

    @Override
    public Mission create(Mission mission) {
        return this.missionRepository.save(mission);
    }

    @Override
    public Optional<Mission> findById(UUID id) {
        return this.missionRepository.findById(id);
    }

    @Override
    public List<Mission> findAll() {
        return this.missionRepository.findAll();
    }

    @Override
    public List<Mission> findByDroneId_Id(UUID droneId) {
        return missionRepository.findByDroneId_Id(droneId);
    }

}
