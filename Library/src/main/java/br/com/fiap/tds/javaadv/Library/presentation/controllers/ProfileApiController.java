package br.com.fiap.tds.javaadv.Library.presentation.controllers;

import br.com.fiap.tds.javaadv.Library.domainmodel.Profile;
import br.com.fiap.tds.javaadv.Library.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileApiController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<Profile>> findAll(){
        return ResponseEntity.ok(this.profileService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> findById(@PathVariable("id") UUID id){
        Profile profile = this.profileService.findById(id);
        if (profile == null)
            return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(profile);
    }

    @PostMapping
    public ResponseEntity<Profile> save(@RequestBody Profile profile){
        Profile newProfile = this.profileService.create(profile);
        return new ResponseEntity<>(newProfile, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestBody UUID id){
        if (!this.profileService.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
        else
            this.profileService.removeById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> update(@PathVariable("id") UUID id,  @RequestBody Profile profile){
        if (!this.profileService.existsById(id))
            return ResponseEntity.notFound().build();

        profile.setId(id);
        return new ResponseEntity<>(
                this.profileService.create(profile),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Profile> partialUpdate(@PathVariable("id") UUID id, @RequestBody Profile profile){
        Profile updatedProfile = null;
        try {
            updatedProfile = this.profileService.findById(id);
            return new ResponseEntity<>(
                    updatedProfile,
                    HttpStatus.CREATED
            );
        } catch (IllegalArgumentException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity.notFound().build();
        }
    }


}
