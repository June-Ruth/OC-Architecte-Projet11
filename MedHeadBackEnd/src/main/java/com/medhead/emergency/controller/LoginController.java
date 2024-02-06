package com.medhead.emergency.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManagerP) {
        authenticationManager = authenticationManagerP;
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestParam String username,
                                @RequestParam String password) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        Authentication authenticationResponse =
                authenticationManager.authenticate(authenticationRequest);
        if(authenticationResponse.isAuthenticated()) {
            return ResponseEntity.ok(authenticationResponse);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity getUser() {
        Map<String, String> message = new HashMap<>();
        message.put("message", "Welcome, User");
        return ResponseEntity.ok(message);
    }

    @GetMapping("/admin")
    public ResponseEntity getAdmin() {
        Map<String, String> message = new HashMap<>();
        message.put("message", "Welcome, Admin");
        return ResponseEntity.ok(message);
    }
}
