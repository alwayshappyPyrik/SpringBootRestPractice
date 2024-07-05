package com.example.springbootrestpractice.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    @Secured({"ROLE_READ"})
    @GetMapping("read/userProfile")
    public String readProfile() {
        return "Welcome to Read Profile";
    }

    @RolesAllowed({"ROLE_WRITE"})
    @GetMapping("write/userProfile")
    public String writeProfile() {
        return "Welcome to Write Profile";
    }

    @PreAuthorize("hasAnyRole('WRITE', 'DELETE')")
    @GetMapping("write_or_delete/userProfile")
    public String adminProfile() {
        return "Welcome to Write or Delete Profile";
    }

    @PreAuthorize("#username == authentication.principal.username")
    @GetMapping("hello-user")
    public String welcomeUser(String username) {
        return "Hello, " + username;
    }
}
