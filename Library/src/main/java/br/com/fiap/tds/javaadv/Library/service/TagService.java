package br.com.fiap.tds.javaadv.Library.service;

import br.com.fiap.tds.javaadv.Library.domainmodel.Tag;

import java.util.List;
import java.util.UUID;

public interface TagService {

    List<Tag> findAll();

    Tag findById(UUID id);

    Tag create(Tag tag);

    boolean existsById(UUID id);

    void removeById(UUID id);

}
