package br.com.fiap.tds.javaadv.Library.domainmodel.repositories;

import java.util.List;

public interface UserRepository<T, ID> {

    List<T> findAll();
    T findById(ID id);
    T create(T user);
    boolean existsById(ID id);
    void removeById(ID id);

}
