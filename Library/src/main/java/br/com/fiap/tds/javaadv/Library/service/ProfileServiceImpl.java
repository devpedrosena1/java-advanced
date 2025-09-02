package br.com.fiap.tds.javaadv.Library.service;

import br.com.fiap.tds.javaadv.Library.domainmodel.Profile;
import br.com.fiap.tds.javaadv.Library.domainmodel.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public List<Profile> findAll() {
        return new ArrayList<>(
                this.profileRepository.findAll()
        );
    }

    @Override
    public Profile findById(UUID id) {
        return this.profileRepository.findById(id).orElse(null);
    }

    @Override
    public Profile create(Profile profile) {
        return this.profileRepository.save(profile);
    }

    @Override
    public boolean existsById(UUID id) {
        return this.profileRepository.existsById(id);
    }

    @Override
    public void removeById(UUID id) {
        this.profileRepository.deleteById(id);
    }
}
