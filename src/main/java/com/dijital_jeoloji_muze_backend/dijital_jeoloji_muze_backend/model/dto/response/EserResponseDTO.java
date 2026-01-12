package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public record EserResponseDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Integer id,
        String isim,
        String qrLink,
        String qrFoto,
        String foto,
        String ses,
        String donem,
        String boyut,
        String getirenKisi,
        LocalDate getirildigiTarih,
        String aciklama,
        Long goruntulenmeSayisi,
        List<EserYorumResponseDTO> eserYorumlari,
        Instant createdAt,
        Instant updatedAt
) {
}
