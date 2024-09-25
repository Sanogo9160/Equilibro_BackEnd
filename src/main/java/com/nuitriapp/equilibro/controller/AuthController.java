package com.nuitriapp.equilibro.controller;

import com.nuitriapp.equilibro.config.JwtUtil;
import com.nuitriapp.equilibro.model.AuthRequest;
import com.nuitriapp.equilibro.service.UtilisateurDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UtilisateurDetailsService utilisateurDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String createAuthenticationToken(@RequestBody AuthRequest authRequest) throws AuthenticationException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getMotDePasse()));

        final UserDetails userDetails = utilisateurDetailsService.loadUserByUsername(authRequest.getEmail());
        return jwtUtil.generateToken(userDetails.getUsername()); // Générer le JWT
    }

}
