package br.com.fiap.tds._tdsq.Library.presentation.controllers.transferObjects;

import br.com.fiap.tds._tdsq.Library.domainmodel.User;
import br.com.fiap.tds._tdsq.Library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users") // http://localhost:8080/api/users
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = new ArrayList<>();
        users.add(this.userService.findAll());
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") UUID id) {
        User user = this.userService.findById(id);
        if (user == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(user);
    }

}
