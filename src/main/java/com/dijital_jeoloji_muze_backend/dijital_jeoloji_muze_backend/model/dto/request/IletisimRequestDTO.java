package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request;

public record IletisimRequestDTO(
        String ad,
        String soyad,
        String email,
        String konu,
        String telefon,
        String mesaj
) {
}
