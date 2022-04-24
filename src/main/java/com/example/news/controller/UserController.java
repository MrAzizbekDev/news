package com.example.news.controller;

import com.example.news.payload.ApiResponse;
import com.example.news.payload.RegisterDto;
import com.example.news.payload.UserDto;
import com.example.news.service.AuthService;
import com.example.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;
    @PostMapping("/register")
    public HttpEntity<?> register( @Valid @RequestBody UserDto userDto){
      ApiResponse apiResponse=userService.register(userDto);
      return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }
}
