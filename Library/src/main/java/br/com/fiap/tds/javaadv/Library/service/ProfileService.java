package br.com.fiap.tds.javaadv.Library.service;

import br.com.fiap.tds.javaadv.Library.domainmodel.Profile;

import java.util.List;
import java.util.UUID;

public interface ProfileService {

    List<Profile> findAll();

    Profile findById(UUID id);

    Profile create(Profile profile);

    boolean existsById(UUID id);

    void removeById(UUID id);

}
