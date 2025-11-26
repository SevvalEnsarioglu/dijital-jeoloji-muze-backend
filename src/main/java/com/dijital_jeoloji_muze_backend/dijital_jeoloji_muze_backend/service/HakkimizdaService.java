package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.HakkimizdaRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.HakkimizdaResponseDTO;

import java.util.Map;

public interface HakkimizdaService {
    HakkimizdaResponseDTO createHakkimizda(HakkimizdaRequestDTO request);

    HakkimizdaResponseDTO getHakkimizda();

    HakkimizdaResponseDTO patchHakkimizda(Map<String, Object> updates);

    HakkimizdaResponseDTO updateHakkimizda(HakkimizdaRequestDTO request);
    void deleteHakkimizda();

}
