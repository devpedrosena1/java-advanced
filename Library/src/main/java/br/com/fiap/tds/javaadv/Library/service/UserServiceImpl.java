package br.com.fiap.tds.javaadv.Library.service;

import br.com.fiap.tds.javaadv.Library.domainmodel.User;
import br.com.fiap.tds.javaadv.Library.domainmodel.repositories.UserRepository;
import br.com.fiap.tds.javaadv.Library.domainmodel.repositories.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;

    @Override
    public List<User> findAll() {
        return new ArrayList<>(
                this.userRepository.findAll()
        );
    }

    @Override
    public User findById(UUID id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public User create(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public boolean existsById(UUID id) {
        return this.userRepository.existsById(id);
    }

    @Override
    public void removeById(UUID id) {
        this.userRepository.deleteById(id);
    }

    public User partialUpdate(UUID id, User user) {
        if (!this.userRepository.existsById(id))
            throw new IllegalArgumentException("Entity not found");

        User userFromDateBase = this.userRepository.findById(id).orElse(null);

        if (!userFromDateBase.getName().equals(user.getName()))
            userFromDateBase.setName(user.getName());

        if (!userFromDateBase.getEmail().equals(user.getEmail()))
            userFromDateBase.setEmail(user.getEmail());

        if (!userFromDateBase.getPassword().equals(user.getPassword()))
            userFromDateBase.setPassword(user.getPassword());

        return this.create(userFromDateBase);
    }
}
