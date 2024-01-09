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

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestParam String username,
                                      @RequestParam String password) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        Authentication authenticationResponse =
                authenticationManager.authenticate(authenticationRequest);
        if(authenticationResponse.isAuthenticated()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity getUser() {
        Map<String, String> map = new HashMap<>();
        map.put("test", "test");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "Welcome, Admin";
    }
}
