package com.example.springbootrestpractice.repository;

import com.example.springbootrestpractice.authorization.Authorities;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password) {
        if (user.equals("yaroslav") && password.equals("12345")) {
            Authorities[] myRules = Authorities.class.getEnumConstants();
            return List.of(myRules);
        } else {
            return List.of();
        }
    }
}
