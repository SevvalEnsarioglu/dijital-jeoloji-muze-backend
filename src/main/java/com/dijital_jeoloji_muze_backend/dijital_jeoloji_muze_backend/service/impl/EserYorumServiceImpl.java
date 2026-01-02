package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.impl;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper.EserYorumMapper;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.EserYorumRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.EserYorumResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.EserYorum;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.EserYorumService;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository.EserYorumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EserYorumServiceImpl implements EserYorumService {

    private final EserYorumRepository eserYorumRepository;
    private final EserYorumMapper eserYorumMapper;

    @Override
    @Transactional
    public EserYorumResponseDTO createEserYorum(EserYorumRequestDTO request) {
        EserYorum entity = eserYorumMapper.toEntity(request);
        EserYorum saved = eserYorumRepository.save(entity);
        return eserYorumMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EserYorumResponseDTO> getAllEserYorum() {
        return eserYorumRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(eserYorumMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EserYorumResponseDTO getEserYorumById(Integer id) {
        return eserYorumRepository.findById(id)
                .map(eserYorumMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Eser Yorumu kaydı bulunamadı. ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EserYorumResponseDTO> getYorumByEserId(Integer eserID) {
        List<EserYorum> yorumList = eserYorumRepository.findByEserID(eserID, Sort.by(Sort.Direction.DESC, "id"));
        return yorumList.stream()
                .map(eserYorumMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public EserYorumResponseDTO updateEserYorum(Integer id, EserYorumRequestDTO request) {
        EserYorum existing = eserYorumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Eser Yorum not found"));

        if (request.adSoyad() != null && !request.adSoyad().isBlank()) {
            existing.setAdSoyad(request.adSoyad());
        }
        if (request.email() != null && !request.email().isBlank()) {
            existing.setEmail(request.email());
        }
        if (request.yorum() != null && !request.yorum().isBlank()) {
            existing.setYorum(request.yorum());
        }
        if (request.puan() != null) {
            existing.setPuan(request.puan());
        }
        if (request.okundu() != null) {
            existing.setOkundu(request.okundu());
        }

        EserYorum updated = eserYorumRepository.save(existing);
        return eserYorumMapper.toResponseDTO(updated);
    }

    @Override
    @Transactional
    public EserYorumResponseDTO updateOkunduDurumu(Integer id, Boolean okundu) {
        EserYorum existing = eserYorumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Eser Yorum not found"));

        existing.setOkundu(okundu);
        EserYorum updated = eserYorumRepository.save(existing);
        return eserYorumMapper.toResponseDTO(updated);
    }

    @Override
    @Transactional
    public void deleteEserYorum(Integer id) {
        if (!eserYorumRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Eser Yorum verisi bulunamadı");

        }
        eserYorumRepository.deleteById(id);
    }
}