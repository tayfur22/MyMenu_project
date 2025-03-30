package com.example.Mymenu.project.controller;

import com.example.Mymenu.project.Enum.RoleName;
import com.example.Mymenu.project.entity.Role;
import com.example.Mymenu.project.entity.User;
import com.example.Mymenu.project.service.RoleService;
import com.example.Mymenu.project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users2")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        Role userRole = roleService.findByName(RoleName.ROLE_USER);
        user.setRoles(Collections.singleton(userRole));

        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable Long id) {
        return ResponseEntity.ok(userService.update(user, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }
}
