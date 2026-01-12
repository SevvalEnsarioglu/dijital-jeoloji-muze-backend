package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.EserRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.EserListResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.EserResponseDTO;

import java.util.List;

public interface EserService {

    EserResponseDTO createEser(EserRequestDTO request);

    EserResponseDTO getEserById(Integer id);

    EserResponseDTO updateEser(Integer id, EserRequestDTO request);

    void deleteEser(Integer id);

    List<EserListResponseDTO> getAllEser(String isim, String sortBy, String sortDirection);
}