package com.example.news.controller;

import com.example.news.entity.User;
import com.example.news.payload.ApiResponse;
import com.example.news.payload.Logindto;
import com.example.news.payload.RegisterDto;
import com.example.news.security.CurrentUser;
import com.example.news.security.JwtProvider;
import com.example.news.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    final AuthService authService;
    final AuthenticationManager authenticationManager;
    final JwtProvider jwtProvider;

    @PostMapping("/register")
    public HttpEntity<?> register( @Valid @RequestBody RegisterDto registerDto){
      ApiResponse apiResponse=authService.register(registerDto);
      return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login( @Valid @RequestBody Logindto logindto){
        Authentication authenticate = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(logindto.getUsername(),
                logindto.getPassword()));

        //
        User user = (User) authenticate.getPrincipal();

        System.out.println(user);
        String token = jwtProvider.generateToken(user.getUsername());
        System.out.printf("token",token);
        return ResponseEntity.ok(token);
    }
    @GetMapping("/me")
    public ResponseEntity getMe(@CurrentUser User user){
        return ResponseEntity.ok(user);
    }
}
