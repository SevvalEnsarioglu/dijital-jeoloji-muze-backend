package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record IletisimResponseDTO (
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String id,
        String ad,
        String soyad,
        String email,
        String konu,
        String telefon,
        String mesaj,
        Instant createdAt,
        Instant updatedAt

){
}
