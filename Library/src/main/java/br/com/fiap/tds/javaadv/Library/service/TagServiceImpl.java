package br.com.fiap.tds.javaadv.Library.service;

import br.com.fiap.tds.javaadv.Library.domainmodel.Tag;
import br.com.fiap.tds.javaadv.Library.domainmodel.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> findAll() {
        return new ArrayList<>(
                this.tagRepository.findAll()
        );
    }

    @Override
    public Tag findById(UUID id) {
        return this.tagRepository.findById(id).orElse(null);
    }

    @Override
    public Tag create(Tag tag) {
        return this.tagRepository.save(tag);
    }

    @Override
    public boolean existsById(UUID id) {
        return this.tagRepository.existsById(id);
    }

    @Override
    public void removeById(UUID id) {
        this.tagRepository.deleteById(id);
    }
}
