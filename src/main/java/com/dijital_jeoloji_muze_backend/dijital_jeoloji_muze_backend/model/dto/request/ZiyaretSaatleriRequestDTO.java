package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request;

import java.time.LocalTime;

public record ZiyaretSaatleriRequestDTO(
        String gun,
        LocalTime acilisSaati,
        LocalTime kapanisSaati
) {
}
