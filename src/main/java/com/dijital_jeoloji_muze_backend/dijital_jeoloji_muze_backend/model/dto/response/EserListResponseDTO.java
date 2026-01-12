package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EserListResponseDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Integer id,
        String isim,
        String foto,
        String aciklama,
        Long goruntulenmeSayisi,
        Long yorumSayisi
) {
}
