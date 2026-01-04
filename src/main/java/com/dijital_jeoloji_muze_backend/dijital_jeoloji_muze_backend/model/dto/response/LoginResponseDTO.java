package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response;

public record LoginResponseDTO(
        String token,
        String username,
        String message
) {
}
