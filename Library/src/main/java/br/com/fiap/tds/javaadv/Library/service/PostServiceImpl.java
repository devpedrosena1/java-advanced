package br.com.fiap.tds.javaadv.Library.service;

import br.com.fiap.tds.javaadv.Library.domainmodel.Post;
import br.com.fiap.tds.javaadv.Library.domainmodel.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(
                this.postRepository.findAll()
        );
    }

    @Override
    public Post findById(UUID id) {
        return this.postRepository.findById(id).orElse(null);
    }

    @Override
    public Post create(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public boolean existsById(UUID id) {
        return this.postRepository.existsById(id);
    }

    @Override
    public void removeById(UUID id) {
        this.postRepository.deleteById(id);
    }
}
