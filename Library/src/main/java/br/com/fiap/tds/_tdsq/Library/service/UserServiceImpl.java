package br.com.fiap.tds._tdsq.Library.service;

import br.com.fiap.tds._tdsq.Library.domainmodel.User;
import br.com.fiap.tds._tdsq.Library.domainmodel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public User findAll() {
        return null;
    }

    @Override
    public User findById(UUID id) {
        return this.userRepository.findById(id);
    }

}
