package br.com.fiap.tds._tdsq.Library.service;

import br.com.fiap.tds._tdsq.Library.domainmodel.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface UserService {

    User findAll();

}
