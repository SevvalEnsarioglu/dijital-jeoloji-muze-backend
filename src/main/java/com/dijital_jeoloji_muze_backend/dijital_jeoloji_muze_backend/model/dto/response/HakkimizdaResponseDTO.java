package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record HakkimizdaResponseDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String id,
        String hakkinda,
        String adres,
        String telefon,
        String email,
        Instant createdAt,
        Instant updatedAt
) {
}
