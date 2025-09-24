package br.com.fiap.tds.javaadv.missiondrone.domainmodel.repositories;

import br.com.fiap.tds.javaadv.missiondrone.domainmodel.QMission;
import br.com.fiap.tds.javaadv.missiondrone.presentation.transferObjects.DroneRankingDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.tds.javaadv.missiondrone.domainmodel.QDrone.drone;
import static br.com.fiap.tds.javaadv.missiondrone.domainmodel.QMission.mission;

public class DroneRepositoryImpl implements DroneRepositoryCustom{

    @PersistenceContext
    private @Setter EntityManager entityManager;

    @Override
    public Optional<Double> findAverageBatteryUsage(UUID droneId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QMission mission = QMission.mission;

        Double averageResult = queryFactory
                .select(mission.estimatedBatteryUsage.avg())
                .from(mission)
                .where(mission.droneId.id.eq(droneId))
                .fetchOne();

        return Optional.ofNullable(averageResult);
    }

    @Override
    public List<DroneRankingDTO> findDroneUsageRanking() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        return queryFactory

                .select(Projections.constructor(DroneRankingDTO.class,
                        drone.id,
                        drone.model,
                        mission.id.count()
                ))
                .from(mission)
                .join(mission.droneId, drone)
                .groupBy(drone.id, drone.model)
                .orderBy(mission.id.count().desc())
                .fetch();
    }
}
