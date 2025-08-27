package br.com.fiap.tds._tdsq.Library.domainmodel.repositories;

import br.com.fiap.tds._tdsq.Library.domainmodel.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class NonPersistentUserRepository implements UserRepository <User, UUID>{

    private List<User> internalData = new ArrayList<>();

    public NonPersistentUserRepository() {

    }

    @Override
    public List<User> findAll(UUID uuid) {
        return this.internalData.stream().toList();
    }

    @Override
    public User findById(UUID id) {
        return this.findById(id);
    }

    @Override
    public User postUser(UUID id) {
        return this.postUser(id);
    }

}
