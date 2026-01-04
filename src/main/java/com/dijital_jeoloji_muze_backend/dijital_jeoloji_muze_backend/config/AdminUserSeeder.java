package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.config;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.AdminUser;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminUserSeeder implements CommandLineRunner {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        if (adminUserRepository.findByUsername(adminUsername).isPresent()) {
            log.info("Admin zaten var: {}", adminUsername);
            return;
        }

        // Admin oluştur
        AdminUser admin = new AdminUser();
        admin.setUsername(adminUsername);
        admin.setPassword(passwordEncoder.encode(adminPassword)); // Şifreyi bcrypt ile şifrele!
        admin.setEmail("admin@dijitaljeoloji.com");
        admin.setIsActive(true);

        adminUserRepository.save(admin);
        log.info("✅ Admin oluşturuldu!");
        log.info("👤 Username: {}", adminUsername);
        log.info("🔐 Password: *** (şifreli)");
    }
}