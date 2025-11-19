package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.impl;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper.DtoMapper;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.HakkimizdaRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.HakkimizdaResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.Hakkimizda;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository.HakkimizdaRepository;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.HakkimizdaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class HakkimizdaServiceImpl implements HakkimizdaService {

    private static final String SINGLETON_ID = "HAKKIMIZDA_SINGLETON_ID";
    private final HakkimizdaRepository hakkimizdaRepository;
    private final DtoMapper dtoMapper;

    public HakkimizdaServiceImpl(HakkimizdaRepository hakkimizdaRepository, DtoMapper dtoMapper) {
        this.hakkimizdaRepository = hakkimizdaRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    @Transactional
    public HakkimizdaResponseDTO createHakkimizda(HakkimizdaRequestDTO request) {
        if (hakkimizdaRepository.existsById(SINGLETON_ID)) {
            throw new IllegalStateException("Hakkimizda zaten mevcut. Güncellemek için update metodunu kullanınız.");
        }

        Hakkimizda entity = dtoMapper.toHakkimizdaEntity(request);
        entity.setId(SINGLETON_ID);
        Hakkimizda saved = hakkimizdaRepository.save(entity);
        return dtoMapper.toHakkimizdaResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public HakkimizdaResponseDTO getHakkimizda() {
        Optional<Hakkimizda> hakkimizda = hakkimizdaRepository.findById(SINGLETON_ID);

        if (hakkimizda.isEmpty()) {
            throw new IllegalStateException("Hakkimizda verisi bulunamadı");
        }

        return dtoMapper.toHakkimizdaResponseDTO(hakkimizda.get());
    }

    @Override
    @Transactional
    public HakkimizdaResponseDTO updateHakkimizda(HakkimizdaRequestDTO request) {
        Hakkimizda existing = hakkimizdaRepository.findById(SINGLETON_ID)
                .orElseThrow(() -> new IllegalStateException(
                        "Hakkimizda verisi bulunamadı. Önce create işlemi yapınız."));

        existing.setHakkinda(request.hakkinda());
        existing.setAdres(request.adres());
        existing.setTelefon(request.telefon());
        existing.setEmail(request.email());
        Hakkimizda updated = hakkimizdaRepository.save(existing);
        return dtoMapper.toHakkimizdaResponseDTO(updated);
    }

    @Override
    @Transactional
    public HakkimizdaResponseDTO patchHakkimizda(Map<String, Object> updates) {
        Hakkimizda existing = hakkimizdaRepository.findById(SINGLETON_ID)
                .orElseThrow(() -> new IllegalStateException(
                        "Hakkimizda verisi bulunamadı. Önce create işlemi yapınız."));

        updates.forEach((key, value) -> {
            if (value == null) {
                return;
            }
            switch (key) {
                case "hakkinda" -> existing.setHakkinda((String) value);
                case "adres" -> existing.setAdres((String) value);
                case "telefon" -> existing.setTelefon((String) value);
                case "email" -> existing.setEmail((String) value);

                default -> throw new IllegalArgumentException("Bilinmeyen alan: " + key);
            }
        });

        Hakkimizda updated = hakkimizdaRepository.save(existing);
        return dtoMapper.toHakkimizdaResponseDTO(updated);
    }

    @Override
    @Transactional
    public void deleteHakkimizda() {
        if (!hakkimizdaRepository.existsById(SINGLETON_ID)) {
            throw new IllegalStateException("Silinecek Hakkimizda verisi bulunamadı");
        }
        hakkimizdaRepository.deleteById(SINGLETON_ID);
    }
}