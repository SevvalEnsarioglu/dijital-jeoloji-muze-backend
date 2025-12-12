package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.impl;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper.ZiyaretSaatleriMapper;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.ZiyaretSaatleriRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.ZiyaretSaatleriResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.ZiyaretSaatleri;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository.ZiyaretSaatleriRepository;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.ZiyaretSaatleriService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ZiyaretSaatleriServiceImpl implements ZiyaretSaatleriService {

    private final ZiyaretSaatleriRepository repository;
    private final ZiyaretSaatleriMapper mapper;

    @Override
    @Transactional
    public ZiyaretSaatleriResponseDTO createZiyaretSaatleri(ZiyaretSaatleriRequestDTO request) {
        ZiyaretSaatleri entity = mapper.toEntity(request);
        ZiyaretSaatleri saved = repository.save(entity);
        return mapper.toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ZiyaretSaatleriResponseDTO> getAllZiyaretSaatleri() {
        return repository.findAll(Sort.by("id"))
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public ZiyaretSaatleriResponseDTO updateZiyaretSaatleri(Integer id, ZiyaretSaatleriRequestDTO request) {
        ZiyaretSaatleri existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ziyaret saatleri bulunamadı"));

        existing.setGun(request.gun());
        existing.setAcilisSaati(request.acilisSaati());
        existing.setKapanisSaati(request.kapanisSaati());

        ZiyaretSaatleri updated = repository.save(existing);
        return mapper.toResponseDTO(updated);
    }

    @Override
    @Transactional
    public void deleteZiyaretSaatleri(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ziyaret saatleri bulunamadı");
        }
        repository.deleteById(id);
    }
}
