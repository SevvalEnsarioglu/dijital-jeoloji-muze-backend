package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request;

public record EserYorumRequestDTO(
        Integer eserID,
        String adSoyad,
        String email,
        String yorum,
        Integer puan,
        Boolean okundu
        ) {
}
