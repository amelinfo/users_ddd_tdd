package com.ameltaleb.users.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ameltaleb.users.domain.exceptions.EmailAlreadyUsedException;
import com.ameltaleb.users.domain.model.User;
import com.ameltaleb.users.domain.port.in.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Endpoints for user operations")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Returns a list of all users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new user")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
    try {
        user.setId(null); // Ignore the id if it is present
        User created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    } catch (EmailAlreadyUsedException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }
    
    @PatchMapping("/{id}/activate")
    @Operation(summary = "Activate a user")
    public ResponseEntity<?> activateUser(@PathVariable Long id) {
        User activated = userService.activateUser(id);
        return ResponseEntity.ok(activated);
    }

    @PatchMapping("/{id}/desactivate")
    @Operation(summary = "Desactivate a user")
    public ResponseEntity<?> desactivateUser(@PathVariable Long id) {
        User activated = userService.desactivateUser(id);
        return ResponseEntity.ok(activated);
    }
}
