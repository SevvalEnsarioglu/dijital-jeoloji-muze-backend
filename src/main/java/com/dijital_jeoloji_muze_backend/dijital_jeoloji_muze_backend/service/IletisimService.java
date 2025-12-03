package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.IletisimRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.IletisimResponseDTO;

import java.util.List;

public interface IletisimService {
    IletisimResponseDTO createIletisim(IletisimRequestDTO request);

    List<IletisimResponseDTO> getAllIletisim();

    IletisimResponseDTO getIletisimById(String id);

    IletisimResponseDTO updateIletisim(String id, IletisimRequestDTO request);

    void deleteIletisim(String id);
}
