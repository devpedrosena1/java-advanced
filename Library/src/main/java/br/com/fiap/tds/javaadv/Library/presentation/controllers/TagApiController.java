package br.com.fiap.tds.javaadv.Library.presentation.controllers;

import br.com.fiap.tds.javaadv.Library.domainmodel.Tag;
import br.com.fiap.tds.javaadv.Library.service.TagService;
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
@RequestMapping("/api/tag")
public class TagApiController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<Tag>> findAll() {
        return ResponseEntity.ok(this.tagService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> findById(@PathVariable("id") UUID id) {
        Tag tag = this.tagService.findById(id);
        if (tag == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(tag);
    }

    @PostMapping
    public ResponseEntity<Tag> save(@RequestBody Tag tag) {
        Tag newTag = this.tagService.create(tag);
        return new ResponseEntity<>(newTag, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestBody UUID id) {
        if (!this.tagService.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found");
        else
            this.tagService.removeById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> update(@PathVariable("id") UUID id, @RequestBody Tag tag) {
        if (!this.tagService.existsById(id))
            return ResponseEntity.notFound().build();

        tag.setId(id);
        return new ResponseEntity<>(
                this.tagService.create(tag),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tag> partialUpdate(@PathVariable("id") UUID id, @RequestBody Tag tag) {
        Tag updatedTag = null;
        try {
            updatedTag = this.tagService.findById(id);
            return new ResponseEntity<>(
                    updatedTag,
                    HttpStatus.CREATED
            );
        } catch (IllegalArgumentException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity.notFound().build();
        }
    }
}
