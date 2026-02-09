package com.portfolio.monitoring.configuration;

import com.portfolio.monitoring.user.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/**"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/admin/login",
                                "/admin/logout",
                                "/css/**",
                                "/js/**",
                                "/favicon.ico").permitAll()

                        // GET 요청은 누구나 접근 가능
                        .requestMatchers(HttpMethod.GET, "/admin/**").permitAll()

                        // POST/PUT/DELETE는 ADMIN 권한 필요
                        .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")
                        // 나머지 요청은 공개
                        .anyRequest().permitAll()
                )
                .formLogin(login -> login
                        .loginPage("/admin/login")
                        .defaultSuccessUrl("/admin/dashboard")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/admin/logout")
                        .logoutSuccessUrl("/admin/login")
                ).sessionManagement(session -> session
                        .maximumSessions(1)
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
