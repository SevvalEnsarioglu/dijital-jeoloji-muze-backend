package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record EserRequestDTO(
        String isim,
        MultipartFile foto,
        MultipartFile ses,
        String donem,
        String boyut,
        String getirenKisi,
        LocalDate getirildigiTarih,
        String aciklama
) {
}
