package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.IletisimMesajiRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.IletisimMesajiResponseDTO;

import java.util.List;

public interface IletisimMesajiService {
    IletisimMesajiResponseDTO createIletisimMesaji(IletisimMesajiRequestDTO request);

    List<IletisimMesajiResponseDTO> getAllIletisimMesaji();

    IletisimMesajiResponseDTO getIletisimMesajiById(String id);

    IletisimMesajiResponseDTO updateIletisimMesaji(String id, IletisimMesajiRequestDTO request);

    void deleteIletisimMesaji(String id);
}
