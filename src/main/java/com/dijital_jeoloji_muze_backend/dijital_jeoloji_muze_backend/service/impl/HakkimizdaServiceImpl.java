package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.impl;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper.DtoMapper;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.HakkimizdaRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.HakkimizdaResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository.HakkimizdaRepository;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.HakkimizdaService;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class HakkimizdaServiceImpl implements HakkimizdaService {

    private static final String SINGLETON_ID = "hakkimizda_singleton_id";
    private final HakkimizdaRepository hakkimizdaRepository;
    private final DtoMapper dtoMapper;

    public HakkimizdaServiceImpl(HakkimizdaRepository hakkimizdaRepository, DtoMapper dtoMapper) {
        this.hakkimizdaRepository = hakkimizdaRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public HakkimizdaResponseDTO createHakkimizda(HakkimizdaRequestDTO request) {
        // TODO
        return null;
    }

    @Override
    public HakkimizdaResponseDTO getHakkimizda() {
        // TODO
        return null;
    }

    @Override
    public HakkimizdaResponseDTO updateHakkimizda(HakkimizdaRequestDTO request) {
        // TODO
        return null;
    }

    @Override
    public HakkimizdaResponseDTO patchHakkimizda(Map<String, Object> updates) {
        // TODO
        return null;
    }

    @Override
    public void deleteHakkimizda() {
        // TODO
    }
}
