package com.example.news.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class KimYozganiniSozlash {

    @Bean
    AuditorAware<Long> auditorAware(){
        return new KimYozganiniBilish();
    }
}
