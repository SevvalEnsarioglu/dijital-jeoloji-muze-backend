package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.impl;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper.AnasayfaMapper;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.AnasayfaResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.Anasayfa;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository.AnasayfaRepository;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.AnasayfaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.Binary;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnasayfaServiceImpl implements AnasayfaService {
    private final AnasayfaRepository anasayfaRepository;
    private final AnasayfaMapper anasayfaMapper;

    // kısıt uyguladık
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final String[] ALLOWED_TYPES = { "image/jpeg", "image/png", "image/gif", "image/webp" };

    @Override
    @Transactional
    public AnasayfaResponseDTO createAnasayfa(String baslik, String aciklama, MultipartFile foto) {
        validateFoto(foto);
        try {
            byte[] fotoBytes = foto.getBytes(); // MultipartFile'dan byte arrayi al
            Binary binaryFoto = new Binary(fotoBytes); // Byte arrayi MongoDB Binary formatına çevir

            Anasayfa entity = new Anasayfa();
            entity.setFoto(binaryFoto);
            entity.setBaslik(baslik);
            entity.setAciklama(aciklama);
            Anasayfa saved = anasayfaRepository.save(entity);
            return anasayfaMapper.toAnasayfaResponseDTO(saved);

        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Foto yükleme başarısız: " + e.getMessage());
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
                .orElseThrow(() -> {
                    log.warn("Anasayfa bulunamadı. ID: {}", id);
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Anasayfa bulunamadı. ID: " + id);
                });
    }

    @Override
    @Transactional
    public AnasayfaResponseDTO updateAnasayfa(Integer id, String baslik, String aciklama, MultipartFile foto) {
        Anasayfa existing = anasayfaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Anasayfa bulunamadı. ID: {}", id);
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Anasayfa bulunamadı. ID: " + id);
                });

        if (foto != null && !foto.isEmpty()) {
            validateFoto(foto);
            try {
                byte[] fotoBytes = foto.getBytes();
                Binary binaryFoto = new Binary(fotoBytes);
                existing.setFoto(binaryFoto);
            } catch (IOException e) {
                log.error("Foto yükleme başarısız oldu: {}", e.getMessage());
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Foto yükleme başarısız: " + e.getMessage());
            }
        }
        if (baslik != null && !baslik.isBlank()) {
            existing.setBaslik(baslik);
            log.info("Başlık güncellendi. ID: {}", id);
        }
        if (aciklama != null && !aciklama.isBlank()) { // isBlank = boş ya da sadece boşluk
            existing.setAciklama(aciklama);
            log.info("Açıklama güncellendi. ID: {}", id);
        }

        Anasayfa updated = anasayfaRepository.save(existing);
        return anasayfaMapper.toAnasayfaResponseDTO(updated);
    }

    @Override
    @Transactional
    public void deleteAnasayfa(Integer id) {
        if (!anasayfaRepository.existsById(id)) {
            log.warn("Anasayfa bulunamadı. ID: {}", id);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Anasayfa bulunamadı. ID: " + id);
        }
        anasayfaRepository.deleteById(id); // Kaydı sil
        log.info("Anasayfa başarıyla silindi. ID: {}", id);
    }

    private void validateFoto(MultipartFile foto) {
        if (foto == null || foto.isEmpty()) {
            log.warn("Foto dosyası boş");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, // HTTP 400
                    "Foto dosyası boş olamaz");
        }
        if (foto.getSize() > MAX_FILE_SIZE) {
            log.warn("Foto çok büyük. Boyut: {} bytes", foto.getSize());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Foto boyutu 5MB'den küçük olmalıdır");
        }

        String contentType = foto.getContentType();
        if (contentType == null || !Arrays.asList(ALLOWED_TYPES).contains(contentType)) {
            log.warn("Desteklenmeyen dosya tipi: {}", contentType);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Sadece JPEG, PNG, GIF ve WebP formatları desteklenir");
        }
    }
}