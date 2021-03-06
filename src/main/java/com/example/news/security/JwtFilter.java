package com.example.news.security;

import com.example.news.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//har bir requestdan oldin kim kiryapti
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token1 = request.getHeader("Authorization");
        if (token1!=null&&token1.startsWith("Bearer")) {

            String token = token1.substring(7);
            if (jwtProvider.validateToken(token)) {
                if (jwtProvider.expireToken(token)) {
                    //username oldi token1dan
                    String userName = jwtProvider.getUserNameFromToken(token);

                    UserDetails userDetails = authService.loadUserByUsername(userName);

                    UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userDetails,
                            userDetails.getPassword(), userDetails.getAuthorities());

                    System.out.println(user);
                    //tizimga kirgan odamni security o'zi un saqlab turibti
                    SecurityContextHolder.getContext().setAuthentication(user);

                    System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
                }
            }
        }
        //http zanjiri
        doFilter(request, response, filterChain);
    }
}
