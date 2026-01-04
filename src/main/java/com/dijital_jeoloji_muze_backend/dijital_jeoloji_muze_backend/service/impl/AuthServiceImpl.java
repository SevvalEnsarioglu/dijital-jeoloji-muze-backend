package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.impl;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.LoginRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.LoginResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.AdminUser;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository.AdminUserRepository;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.security.JwtTokenProvider;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AdminUserRepository adminUserRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        AdminUser admin = adminUserRepository.findByUsername(request.username())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Kullanıcı adı veya şifre hatalı"));

        if (!admin.getIsActive()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Admin hesabı aktif değil");
        }
        if (!passwordEncoder.matches(request.password(), admin.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Kullanıcı adı veya şifre hatalı");
        }
        String token = jwtTokenProvider.generateToken(admin.getUsername());
        log.info("Admin login başarılı: {}", admin.getUsername());

        return new LoginResponseDTO(
                token,
                admin.getUsername(),
                "Login başarılı");
    }
}