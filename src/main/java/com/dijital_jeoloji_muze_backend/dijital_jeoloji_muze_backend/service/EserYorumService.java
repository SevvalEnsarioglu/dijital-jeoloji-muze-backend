package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.EserYorumRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.EserYorumResponseDTO;

import java.util.List;

public interface EserYorumService {
    EserYorumResponseDTO createEserYorum(EserYorumRequestDTO request);

    List<EserYorumResponseDTO> getAllEserYorum();
    
    EserYorumResponseDTO getEserYorumById(Integer id);


    EserYorumResponseDTO updateEserYorum(Integer id, EserYorumRequestDTO request);

    EserYorumResponseDTO updateOkunduDurumu(Integer id, Boolean okundu);

    void deleteEserYorum(Integer id);

    List<EserYorumResponseDTO> getYorumByEserId(Integer eserID, String sortBy, String sortDirection);
}
