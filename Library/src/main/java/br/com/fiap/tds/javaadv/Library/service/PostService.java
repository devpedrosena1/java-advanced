package br.com.fiap.tds.javaadv.Library.service;

import br.com.fiap.tds.javaadv.Library.domainmodel.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<Post> findAll();

    Post findById(UUID id);

    Post create(Post post);

    boolean existsById(UUID id);

    void removeById(UUID id);

}
