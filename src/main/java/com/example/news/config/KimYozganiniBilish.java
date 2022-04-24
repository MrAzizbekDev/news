package com.example.news.config;

import com.example.news.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class KimYozganiniBilish implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication !=null&&
         authentication.isAuthenticated()&&
        !authentication.getAuthorities().equals("anonymousUser")){
            User user = (User) authentication.getPrincipal();

            return Optional.of(user.getId());
        }
     return Optional.empty();
    }
}
