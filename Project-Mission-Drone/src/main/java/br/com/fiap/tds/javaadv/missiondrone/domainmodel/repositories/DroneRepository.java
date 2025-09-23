package br.com.fiap.tds.javaadv.missiondrone.domainmodel.repositories;

import br.com.fiap.tds.javaadv.missiondrone.domainmodel.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.UUID;

public interface DroneRepository extends
        JpaRepository<Drone, UUID>,
        QuerydslPredicateExecutor<Drone>,
        DroneRepositoryCustom {
}
