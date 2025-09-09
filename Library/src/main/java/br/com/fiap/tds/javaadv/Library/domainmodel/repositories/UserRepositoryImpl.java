package br.com.fiap.tds.javaadv.Library.domainmodel.repositories;

import br.com.fiap.tds.javaadv.Library.domainmodel.Post;
import br.com.fiap.tds.javaadv.Library.domainmodel.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepositoryImpl extends JpaRepository<User, UUID> {

    List<User> findByEmail(String email);
    List<User> findByNameAndEmail(String name, String email);
    List<User> findByPosts(List<Post> post);

}
