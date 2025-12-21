package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.impl;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper.AnasayfaMapper;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.AnasayfaResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.Anasayfa;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository.AnasayfaRepository;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.AnasayfaService;
import lombok.RequiredArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnasayfaServiceImpl implements AnasayfaService {

    private final AnasayfaRepository anasayfaRepository;
    private final AnasayfaMapper anasayfaMapper;

    @Override
    @Transactional
    public AnasayfaResponseDTO createAnasayfa(String aciklama, MultipartFile foto) {
        try {
            byte[] fotoBytes = foto.getBytes();
            String base64Foto = Base64.getEncoder().encodeToString(fotoBytes);

            Anasayfa entity = new Anasayfa();
            entity.setFoto(new Binary(fotoBytes));
            entity.setFotoData(base64Foto);
            entity.setAciklama(aciklama);

            Anasayfa saved = anasayfaRepository.save(entity);
            return anasayfaMapper.toAnasayfaResponseDTO(saved);
        } catch (IOException e) {
            throw new RuntimeException("Foto yükleme başarısız oldu: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnasayfaResponseDTO> getAllAnasayfa() {
        return anasayfaRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(anasayfaMapper::toAnasayfaResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AnasayfaResponseDTO getAnasayfaById(Integer id) {
        return anasayfaRepository.findById(id)
                .map(anasayfaMapper::toAnasayfaResponseDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anasayfa bulunamadı. ID: " + id));
    }

    @Override
    @Transactional
    public AnasayfaResponseDTO updateAnasayfa(Integer id, String aciklama, MultipartFile foto) {
        try {
            Anasayfa existing = anasayfaRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anasayfa bulunamadı. ID: " + id));

            if (foto != null && !foto.isEmpty()) {
                byte[] fotoBytes = foto.getBytes();
                String base64Foto = Base64.getEncoder().encodeToString(fotoBytes);
                existing.setFoto(new Binary(fotoBytes));
                existing.setFotoData(base64Foto);
            }

            existing.setAciklama(aciklama);
            Anasayfa updated = anasayfaRepository.save(existing);
            return anasayfaMapper.toAnasayfaResponseDTO(updated);
        } catch (IOException e) {
            throw new RuntimeException("Foto yükleme başarısız oldu: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteAnasayfa(Integer id) {
        if (!anasayfaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anasayfa bulunamadı. ID: " + id);
        }
        anasayfaRepository.deleteById(id);
    }
}