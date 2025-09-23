package br.com.fiap.tds.javaadv.missiondrone.domainmodel.repositories;

import br.com.fiap.tds.javaadv.missiondrone.domainmodel.QMission;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

//    @Override
//    public List findDroneUsageRanking() {
//        return List.of();
//    }
}
