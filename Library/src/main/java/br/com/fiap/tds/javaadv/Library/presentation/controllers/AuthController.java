package br.com.fiap.tds.javaadv.Library.presentation.controllers;

import br.com.fiap.tds.javaadv.Library.infrastructure.config.JwtHelper;
import br.com.fiap.tds.javaadv.Library.presentation.transferObjects.AuthRequest;
import br.com.fiap.tds.javaadv.Library.presentation.transferObjects.AuthResponse;
import br.com.fiap.tds.javaadv.Library.presentation.transferObjects.TokenRefreshRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AuthenticationManager authenticatorManager;
    @Autowired private JwtHelper jwtHelper;
    @Autowired private UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<?> login (@RequestBody AuthRequest request) {
        Authentication authentication = authenticatorManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtHelper.generateToken(userDetails);
        String refreshToken = jwtHelper.generateRefreshToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody TokenRefreshRequest request) {
        String refreshToken = request.refreshToken();
        String username = jwtHelper.extractUsername(refreshToken);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtHelper.isTokenValid(refreshToken, userDetails)) {
            String newAccessToken = jwtHelper.generateToken(userDetails);
            // Retorna AuthResponse (OK)
            return ResponseEntity.ok(new AuthResponse(newAccessToken, refreshToken));
        } else {
            // Retorna String (OK)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("RefreshToken invalido ou expirado");
        }
    }

}
