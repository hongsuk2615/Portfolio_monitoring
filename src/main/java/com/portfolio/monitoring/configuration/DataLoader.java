package com.portfolio.monitoring.configuration;

import com.portfolio.monitoring.user.entity.User;
import com.portfolio.monitoring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("!test")
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.username:admin}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        // admin 계정이 없으면 생성
        if (userRepository.findByUserName(adminUsername).isEmpty()) {
            User admin = new User();
            admin.setUserName(adminUsername);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole("ADMIN");

            userRepository.save(admin);
            log.info("✅ Admin 계정이 생성되었습니다. (username: {})", adminUsername);
        } else {
            log.info("ℹ️  Admin 계정이 이미 존재합니다.");
        }
    }
}
