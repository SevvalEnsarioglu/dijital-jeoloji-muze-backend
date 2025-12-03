package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.impl;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper.IletisimMapper;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.IletisimRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.IletisimResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.Iletisim;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository.IletisimRepository;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.IletisimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class IletisimServiceImpl implements IletisimService {

    private final IletisimRepository iletisimRepository;
    private final IletisimMapper iletisimMapper;

    @Override
    @Transactional
    public IletisimResponseDTO createIletisim(IletisimRequestDTO request){
        Iletisim entity = iletisimMapper.toIletisimEntity(request);
        Iletisim saved = iletisimRepository.save(entity);
        return iletisimMapper.toIletisimResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IletisimResponseDTO> getAllIletisim(){
        return iletisimRepository.findAll(Sort.by("id"))
                .stream()
                .map(iletisimMapper::toIletisimResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public IletisimResponseDTO getIletisimById(String id){
        return iletisimRepository.findById(id)
                .map(iletisimMapper::toIletisimResponseDTO)
                .orElseThrow(() -> new RuntimeException("İletişim kaydı bulunamadı. ID: " + id));
    }

    @Override
    @Transactional
    public IletisimResponseDTO updateIletisim(String id, IletisimRequestDTO request){
        Iletisim existing = iletisimRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Iletisim not found"));

        existing.setAd(request.ad());
        existing.setSoyad(request.soyad());
        existing.setEmail(request.email());
        existing.setKonu(request.konu());
        existing.setTelefon(request.telefon());
        existing.setMesaj(request.mesaj());

        Iletisim updated = iletisimRepository.save(existing);
        return iletisimMapper.toIletisimResponseDTO(updated);
    }

    @Override
    @Transactional
    public void deleteIletisim(String id) {
        if (!iletisimRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Iletisim verisi bulunamadı");
        }
        iletisimRepository.deleteById(id);
    }
}
