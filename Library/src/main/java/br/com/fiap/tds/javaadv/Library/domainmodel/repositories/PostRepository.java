package br.com.fiap.tds.javaadv.Library.domainmodel.repositories;

import br.com.fiap.tds.javaadv.Library.domainmodel.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
