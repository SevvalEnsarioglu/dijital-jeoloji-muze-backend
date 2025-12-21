package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response;

import java.time.Instant;

public record AnasayfaResponseDTO(
        Integer id,
        String fotoData,
        String aciklama,
        Instant createdAt,
        Instant updatedAt
) {}