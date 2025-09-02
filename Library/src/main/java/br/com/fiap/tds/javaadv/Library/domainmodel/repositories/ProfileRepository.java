package br.com.fiap.tds.javaadv.Library.domainmodel.repositories;

import br.com.fiap.tds.javaadv.Library.domainmodel.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

}
