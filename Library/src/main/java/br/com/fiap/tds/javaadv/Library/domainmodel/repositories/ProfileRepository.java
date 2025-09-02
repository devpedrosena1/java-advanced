package br.com.fiap.tds.javaadv.Library.domainmodel.repositories;

import br.com.fiap.tds.javaadv.Library.domainmodel.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

}
