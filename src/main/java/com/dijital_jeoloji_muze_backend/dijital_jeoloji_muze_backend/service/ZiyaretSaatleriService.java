package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.ZiyaretSaatleriRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.ZiyaretSaatleriResponseDTO;

import java.util.List;

public interface ZiyaretSaatleriService {

    ZiyaretSaatleriResponseDTO createZiyaretSaatleri(ZiyaretSaatleriRequestDTO request);

    List<ZiyaretSaatleriResponseDTO> getAllZiyaretSaatleri();

    ZiyaretSaatleriResponseDTO updateZiyaretSaatleri(Integer id, ZiyaretSaatleriRequestDTO request);

    void deleteZiyaretSaatleri(Integer id);
}
