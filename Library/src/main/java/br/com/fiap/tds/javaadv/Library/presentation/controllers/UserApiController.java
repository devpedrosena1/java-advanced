package br.com.fiap.tds.javaadv.Library.presentation.controllers;

import br.com.fiap.tds.javaadv.Library.domainmodel.User;
import br.com.fiap.tds.javaadv.Library.presentation.transferObjects.UserDTO;
import br.com.fiap.tds.javaadv.Library.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Operacões relacionadas a controle de usuários")
public class UserApiController {

    private final UserService userService;

    @Operation(summary = "Listar todos os usuários", method = "GET")
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(this.userService.findAll());
    }

    @Operation(summary = "Listar todos os usuários", method = "GET")
    @GetMapping("/{id}") //http://localhost:8080/api/users/1
    public ResponseEntity<UserDTO> findById(@PathVariable("id") UUID id){
//        User user = this.userService.findById(id);
//        if(user == null)
//            return ResponseEntity.notFound().build();
//        else
//            return ResponseEntity.ok(user);
        return this.userService.findById(id)
                .map(user -> ResponseEntity.ok(UserDTO.fromEntity(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Insere novo usuário", method = "POST")
    @PostMapping
    public ResponseEntity<UserDTO> save( @Valid @RequestBody UserDTO userDto){
        User newUser = this.userService.create(UserDTO.toEntity(userDto));
        return new ResponseEntity<>(UserDTO.fromEntity(newUser), HttpStatus.CREATED);
    }

    @Operation(summary = "Remove um usuário pelo ID", method = "DELETE")
    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestBody UUID id){
        if( !this.userService.existsById(id ))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        this.userService.removeById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remove um usuário inteiro", method = "DELETE")
    @DeleteMapping("/removeObject")
    public ResponseEntity<Void> delete(@RequestBody UserDTO user){
        if(!this.userService.existsById(user.getId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        this.userService.remove(UserDTO.toEntity(user));
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza um usuário inteiro", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") UUID id, @Valid @RequestBody UserDTO userDto){
        if( !this.userService.existsById(id) )
            return ResponseEntity.notFound().build();
        User user = UserDTO.toEntity(userDto);
        user.setId(id);
        return new ResponseEntity<>(
                UserDTO.fromEntity(this.userService.create(user)),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Atualiza parcialmente o usuário", method = "PATCH")
    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> partialUpdate(@PathVariable("id") UUID id, @Valid @RequestBody UserDTO userDto) {
        User updatedUser = null;
        try {
            updatedUser = this.userService.findById(userDto.getId()).orElse(null);
            return new ResponseEntity<>(
                    UserDTO.fromEntity(updatedUser),
                    HttpStatus.CREATED
            );
        } catch (IllegalArgumentException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Busca usuário pelo nome", method = "GET")
    @GetMapping("/name")
    public ResponseEntity<List<UserDTO>> findAllByName(@RequestParam String name){
        return null;
    }

    //http://localhost:8080/api/users?email=teste@gmail.com
    @Operation(summary = "Busca usuário pelo e-mail parametrizado", method = "GET")
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> findAllByEmail(@RequestParam String email){

        return ResponseEntity.ok(
                new ArrayList<>(
                        this.userService.findByEmail(email)
                                .stream()
                                .map(UserDTO::fromEntity)
                                .toList()));

    }


    //http://localhost:8080/api/users/paged?page=0&size=10&sort=name,desc
    @Operation(summary = "Busca todos os usuários paginados", method = "GET")
    @GetMapping("paged")
    public ResponseEntity<Page<UserDTO>> findAllPaged(Pageable pageable){
        return ResponseEntity.ok(
                this.userService.findAllPaged(pageable)
                        .map(UserDTO::fromEntity)
        );
    }
}
