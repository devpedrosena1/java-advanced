package br.com.fiap.tds.javaadv.missiondrone.domainmodel.repositories;

import java.util.List;
import java.util.Optional;

public interface DroneRepositoryCustom<Drone, UUID> {

    Optional<Double> findAverageBatteryUsage(UUID droneId);
    List<Drone> findDroneUsageRanking();

}
