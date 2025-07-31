package com.example.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HeaderRolesAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        // Only set auth if it's not already set and header is present
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String rolesHeader = request.getHeader("X-Roles");
            if (rolesHeader != null && !rolesHeader.isBlank()) {
                List<SimpleGrantedAuthority> authorities = Arrays.stream(rolesHeader.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r)
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                var auth = new UsernamePasswordAuthenticationToken("user", null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);
    }
}

