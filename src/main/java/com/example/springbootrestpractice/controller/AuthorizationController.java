package com.example.springbootrestpractice.controller;

import com.example.springbootrestpractice.authorization.Authorities;
import com.example.springbootrestpractice.model.AuthorizationUser;
import com.example.springbootrestpractice.model.User;
import com.example.springbootrestpractice.service.AuthorizationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class AuthorizationController {

    private final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("authorize")
    public List<Authorities> getAuthorities(@AuthorizationUser User user) {
        return service.getAuthorities(user);
    }
}
