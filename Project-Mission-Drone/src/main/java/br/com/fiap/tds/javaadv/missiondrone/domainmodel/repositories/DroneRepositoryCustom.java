package br.com.fiap.tds.javaadv.missiondrone.domainmodel.repositories;

import br.com.fiap.tds.javaadv.missiondrone.domainmodel.Drone;
import br.com.fiap.tds.javaadv.missiondrone.presentation.transferObjects.DroneRankingDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DroneRepositoryCustom {

    Optional<Double> findAverageBatteryUsage(UUID droneId);
    List<DroneRankingDTO> findDroneUsageRanking();

}
