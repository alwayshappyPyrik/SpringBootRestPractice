package com.example.springbootrestpractice.service;

import com.example.springbootrestpractice.authorization.Authorities;
import com.example.springbootrestpractice.authorization.InvalidCredentials;
import com.example.springbootrestpractice.authorization.UnauthorizedUser;
import com.example.springbootrestpractice.model.User;
import com.example.springbootrestpractice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(User user) {
        String name = user.getName();
        String password = user.getPassword();
        if (name.isEmpty() || password.isEmpty()) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(name, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + name);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
