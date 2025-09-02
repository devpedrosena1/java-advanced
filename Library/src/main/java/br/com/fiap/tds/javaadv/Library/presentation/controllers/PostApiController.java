package br.com.fiap.tds.javaadv.Library.presentation.controllers;

import br.com.fiap.tds.javaadv.Library.domainmodel.Post;
import br.com.fiap.tds.javaadv.Library.service.PostService;
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
@RequestMapping("/api/posts")
public class PostApiController {

    private final PostService  postService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable("id") UUID id) {
        Post post = this.postService.findById(id);
        if (post == null)
            return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<Post> save(@RequestBody Post post) {
        Post newPost = this.postService.create(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestBody UUID id) {
        if (!this.postService.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found.");
        else
            this.postService.removeById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable("id") UUID id, @RequestBody Post post) {
        if (!this.postService.existsById(id))
            return ResponseEntity.notFound().build();

        post.setId(id);
        return new ResponseEntity<> (
                this.postService.create(post),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Post> partialUpdate(@PathVariable("id") UUID id, @RequestBody Post post) {
        Post updatedPost = null;
        try {
            updatedPost = this.postService.findById(id);
            return new ResponseEntity<> (
                    updatedPost,
                    HttpStatus.CREATED
            );
        } catch (IllegalArgumentException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity.notFound().build();
        }
    }
}
