package com.example.news.service;

import com.example.news.entity.User;
import com.example.news.entity.enums.RoleConstants;
import com.example.news.exceptions.ResourceNotFoundExceptions;
import com.example.news.payload.ApiResponse;
import com.example.news.payload.RegisterDto;
import com.example.news.repository.RoleRepository;
import com.example.news.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder passwordEncoder;

    public ApiResponse register(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword()))
            return new ApiResponse("Passwords not equals", false);

        boolean b = userRepository.existsByUsername(registerDto.getUsername());
        if (b) return new ApiResponse("Bunday username avval royhatdan otgan", false);

        User user=new User(registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByName(RoleConstants.USER).orElseThrow(() ->
                        new ResourceNotFoundExceptions("lavozim",
                                "name",RoleConstants.USER)),
                true

                );
        userRepository.save(user);
        return new ApiResponse("Success you registered !!! ( : )",true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        for (User user : userRepository.findAll()) {
            if (user.getUsername().equals(username)) {
                return user;
            }

        }
        throw  new UsernameNotFoundException(username);

//        return userRepository.findByUsername(username).orElseThrow(() ->
//                new UsernameNotFoundException(username));
    }

//    public UserDetails loadUserByUsername(String userName) {
//
//        return userRepository.findByUsername(userName).orElseThrow(()
//
//                -> new UsernameNotFoundException(userName));
//    }
}
