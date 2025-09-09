package br.com.fiap.tds.javaadv.Library.domainmodel.repositories;

import br.com.fiap.tds.javaadv.Library.domainmodel.Post;
import br.com.fiap.tds.javaadv.Library.domainmodel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByEmail(String email);
    List<User> findByNameAndEmail(String name, String email);
    List<User> findByPosts(List<Post> post);

    //JPQL - Java Persistence Query Language
    @Query("SELECT u FROM User u WHERE u.name = :name")
    List<User> findByName(String name);

}
