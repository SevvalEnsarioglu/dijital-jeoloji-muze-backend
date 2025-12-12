package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalTime;



public record ZiyaretSaatleriResponseDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Integer id,
        String gun,
        LocalTime acilisSaati,
        LocalTime kapanisSaati,
        Instant createdAt,
        Instant updatedAt
) {
}
