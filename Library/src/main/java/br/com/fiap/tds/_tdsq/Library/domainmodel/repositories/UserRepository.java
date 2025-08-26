package br.com.fiap.tds._tdsq.Library.domainmodel.repositories;

import br.com.fiap.tds._tdsq.Library.domainmodel.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository <T, ID>{

    List<T> findAll(ID id);

    User findById(UUID id);

}
