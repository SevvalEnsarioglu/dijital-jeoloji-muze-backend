package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.impl;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper.IletisimMesajiMapper;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.IletisimMesajiRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.IletisimMesajiResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.IletisimMesaji;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository.IletisimMesajiRepository;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.IletisimMesajiService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class IletisimMesajiServiceImpl implements IletisimMesajiService {

    private final IletisimMesajiRepository iletisimMesajiRepository;
    private final IletisimMesajiMapper iletisimMesajiMapper;

    @Override
    @Transactional
    public IletisimMesajiResponseDTO createIletisimMesaji(IletisimMesajiRequestDTO request){
        IletisimMesaji entity = iletisimMesajiMapper.toIletisimMesajiEntity(request);
        IletisimMesaji saved = iletisimMesajiRepository.save(entity);
        return iletisimMesajiMapper.toIletisimMesajiResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IletisimMesajiResponseDTO> getAllIletisimMesaji(){
        return iletisimMesajiRepository.findAll(Sort.by("id"))
                .stream()
                .map(iletisimMesajiMapper::toIletisimMesajiResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public IletisimMesajiResponseDTO getIletisimMesajiById(Integer id){
        return iletisimMesajiRepository.findById(id)
                .map(iletisimMesajiMapper::toIletisimMesajiResponseDTO)
                .orElseThrow(() -> new RuntimeException("İletişim kaydı bulunamadı. ID: " + id));
    }

    @Override
    @Transactional
    public IletisimMesajiResponseDTO updateIletisimMesaji(Integer id, IletisimMesajiRequestDTO request){
        IletisimMesaji existing = iletisimMesajiRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Iletisim not found"));

        existing.setAd(request.ad());
        existing.setSoyad(request.soyad());
        existing.setEmail(request.email());
        existing.setKonu(request.konu());
        existing.setTelefon(request.telefon());
        existing.setMesajiniz(request.mesaj());
        existing.setOkundu(request.okundu());

        IletisimMesaji updated = iletisimMesajiRepository.save(existing);
        return iletisimMesajiMapper.toIletisimMesajiResponseDTO(updated);
    }

    @Override
    @Transactional
    public void deleteIletisimMesaji(Integer id) {
        if (!iletisimMesajiRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Iletisim verisi bulunamadı");
        }
        iletisimMesajiRepository.deleteById(id);
    }

    @Override
    @Transactional
    public IletisimMesajiResponseDTO updateOkunduDurumu(Integer id, Boolean okundu) {
        IletisimMesaji existing = iletisimMesajiRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Iletisim mesajı bulunamadı. ID: " + id));

        existing.setOkundu(okundu);
        IletisimMesaji updated = iletisimMesajiRepository.save(existing);
        return iletisimMesajiMapper.toIletisimMesajiResponseDTO(updated);
    }
}
