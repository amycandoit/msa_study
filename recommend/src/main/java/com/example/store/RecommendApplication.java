package com.example.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@SpringBootApplication
public class RecommendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecommendApplication.class, args);
    }

    @Bean
    AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(userService);
//        provider.setUserDetailsService(oauth2UserService);
//        provider.setO
//        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }
}
