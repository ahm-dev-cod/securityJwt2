package com.example.myuser.controllers;

import com.example.myuser.dto.AuthRequest;
import com.example.myuser.entities.MyUser;
import com.example.myuser.services.MyUserDetailsService;
import com.example.myuser.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private MyUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        System.out.println("Tentative de login pour : " + authRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        System.out.println("Authentification réussie pour : " + authRequest.getUsername());
        MyUser user = (MyUser) authentication.getPrincipal(); // Récupère l’utilisateur authentifié
        String token = jwtUtil.generateToken(user);
        System.out.println("Token généré pour : " + authRequest.getUsername());
        return ResponseEntity.ok(token);
    }
}
