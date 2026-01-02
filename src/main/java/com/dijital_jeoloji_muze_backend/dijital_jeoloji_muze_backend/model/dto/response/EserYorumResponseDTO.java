package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response;

import java.time.Instant;

public record EserYorumResponseDTO(
        String id,
        String eserID,
        String adSoyad,
        String email,
        String yorum,
        Integer puan,
        Boolean okundu,
        Instant createdAt,
        Instant updatedAt
) {
}
