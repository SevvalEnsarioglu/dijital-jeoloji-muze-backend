package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.time.LocalDate;

public record EserResponseDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Integer id,
        String isim,
        String qrLink,
        MultipartFile qrFoto,
        MultipartFile foto,
        MultipartFile ses,
        String donem,
        String boyut,
        String getirenKisi,
        LocalDate getirildigiTarih,
        String aciklama,
        Long goruntulenmeSayisi,
        Instant createdAt,
        Instant updatedAt
) {
}
