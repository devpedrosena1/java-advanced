package br.com.fiap.tds.javaadv.missiondrone.domainmodel.repositories;

import br.com.fiap.tds.javaadv.missiondrone.domainmodel.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MissionRepository extends JpaRepository<Mission, UUID> {
}
