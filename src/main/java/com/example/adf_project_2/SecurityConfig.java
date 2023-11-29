package com.example.adf_project_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    //GET property/ies (excluding tenant data) - anyone
    //GET, CREATE & EDIT tenants - authenticated users
    //CREATE & EDIT properties - managers
    //GET, CREATE & EDIT users - managers

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/tenants/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/tenants/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/tenants/**").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/tenants/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/properties/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/properties/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/properties/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PATCH, "/properties/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/users/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/users/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PATCH, "/users/**").hasRole("MANAGER")
                        .requestMatchers("/graphql").permitAll()
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    InMemoryUserDetailsManager users(){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("secret"))
                .roles("OFFICE", "MANAGER")
                .build();

        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("secret"))
                .roles("OFFICE")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
