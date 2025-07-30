package com.example.config;

import com.example.security.HeaderRolesAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/hello-world/hello").permitAll()
                        .requestMatchers("/api/hello-world/helloAuthUser").hasAnyAuthority("ROLE_USER")
                        .requestMatchers("/api/hello-world/helloAuthAdmin").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new HeaderRolesAuthFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
