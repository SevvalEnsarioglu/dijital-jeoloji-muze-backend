package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.impl;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper.EserMapper;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.EserRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.EserResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.Eser;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository.EserRepository;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.EserService;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.util.QrCodeGenerator;
import org.springframework.beans.factory.annotation.Value;
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
public class EserServiceImpl implements EserService {

    private final EserRepository eserRepository;
    private final EserMapper eserMapper;
    private final QrCodeGenerator qrCodeGenerator;

    @Value("${app.frontend.base-url}")
    private String frontendBaseUrl;

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    private static final String[] ALLOWED_IMAGE_TYPES = { "image/jpeg", "image/png", "image/gif", "image/webp" };
    private static final String[] ALLOWED_AUDIO_TYPES = { "audio/mpeg", "audio/wav", "audio/ogg", "audio/webm" };

    @Override
    @Transactional
    public EserResponseDTO createEser(EserRequestDTO request) {
        validateFoto(request.foto());
        if (request.ses() != null) validateSes(request.ses());

        try {
            Eser entity = new Eser();
            entity.setIsim(request.isim());
            entity.setDonem(request.donem());
            entity.setBoyut(request.boyut());
            entity.setGetirenKisi(request.getirenKisi());
            entity.setGetirildigiTarih(request.getirildigiTarih());
            entity.setAciklama(request.aciklama());
            entity.setGoruntulenmeSayisi(0L);

            if (request.foto() != null && !request.foto().isEmpty()) {
                byte[] fotoBytes = request.foto().getBytes();
                entity.setFoto(new Binary(fotoBytes));
            }

            if (request.ses() != null && !request.ses().isEmpty()) {
                byte[] sesBytes = request.ses().getBytes();
                entity.setSes(new Binary(sesBytes));
            }

            Eser saved = eserRepository.save(entity);

            String qrLink = generateQrLink(saved.getId());
            byte[] qrCodeBytes = qrCodeGenerator.generateQrCode(qrLink, 300, 300);

            saved.setQrFoto(new Binary(qrCodeBytes));
            saved.setQrLink(qrLink);

            Eser updatedWithQr = eserRepository.save(saved);

            return eserMapper.toEserResponseDTO(updatedWithQr);

        } catch (IOException e) {
            log.error("Dosya yazma hatası: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dosya yükleme başarısız");
        } catch (Exception e) {
            log.error("QR Kod üretim hatası: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "QR kod oluşturma başarısız");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<EserResponseDTO> getAllEser() {
        return eserRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(eserMapper::toEserResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public EserResponseDTO getEserById(Integer id) {
        Eser eser = eserRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Eser bulunamadı. ID: {}", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Eser bulunamadı. ID: " + id);
                });

        eser.setGoruntulenmeSayisi(eser.getGoruntulenmeSayisi() + 1);
        Eser saved = eserRepository.save(eser);
        return eserMapper.toEserResponseDTO(saved);
    }

    @Override
    @Transactional
    public EserResponseDTO updateEser(Integer id, EserRequestDTO request) {
        Eser existing = eserRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Güncellenecek eser bulunamadı. ID: {}", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Eser bulunamadı. ID: " + id);
                });

        try {
            if (request.isim() != null && !request.isim().isBlank()) existing.setIsim(request.isim());
            if (request.donem() != null && !request.donem().isBlank()) existing.setDonem(request.donem());
            if (request.boyut() != null && !request.boyut().isBlank()) existing.setBoyut(request.boyut());
            if (request.getirenKisi() != null && !request.getirenKisi().isBlank()) existing.setGetirenKisi(request.getirenKisi());
            if (request.getirildigiTarih() != null) existing.setGetirildigiTarih(request.getirildigiTarih());
            if (request.aciklama() != null && !request.aciklama().isBlank()) existing.setAciklama(request.aciklama());

            if (request.foto() != null && !request.foto().isEmpty()) {
                validateFoto(request.foto());
                existing.setFoto(new Binary(request.foto().getBytes()));
                log.info("Eser fotoğrafı güncellendi. ID: {}", id);
            }

            if (request.ses() != null && !request.ses().isEmpty()) {
                validateSes(request.ses());
                existing.setSes(new Binary(request.ses().getBytes()));
                log.info("Eser ses dosyası güncellendi. ID: {}", id);
            }

            Eser updated = eserRepository.save(existing);
            return eserMapper.toEserResponseDTO(updated);

        } catch (IOException e) {
            log.error("Güncelleme sırasında dosya hatası: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dosya yükleme başarısız");
        }
    }

    @Override
    @Transactional
    public void deleteEser(Integer id) {
        if (!eserRepository.existsById(id)) {
            log.warn("Silinmek istenen eser bulunamadı. ID: {}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Eser bulunamadı. ID: " + id);
        }
        eserRepository.deleteById(id);
        log.info("Eser başarıyla silindi. ID: {}", id);
    }

    private void validateFoto(MultipartFile foto) {
        if (foto == null || foto.isEmpty()) {
            log.warn("Fotoğraf dosyası eksik");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Foto dosyası boş olamaz");
        }
        commonValidation(foto, ALLOWED_IMAGE_TYPES, "Fotoğraf");
    }

    private void validateSes(MultipartFile ses) {
        if (ses == null || ses.isEmpty()) return;
        commonValidation(ses, ALLOWED_AUDIO_TYPES, "Ses");
    }

    private void commonValidation(MultipartFile file, String[] allowedTypes, String label) {
        if (file.getSize() > MAX_FILE_SIZE) {
            log.warn("{} dosyası limit üstü. Boyut: {} bytes", label, file.getSize());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, label + " boyutu 5MB'den küçük olmalıdır");
        }

        String contentType = file.getContentType();
        if (contentType == null || !Arrays.asList(allowedTypes).contains(contentType)) {
            log.warn("Geçersiz {} formatı: {}", label, contentType);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Desteklenmeyen " + label + " formatı");
        }
    }

    private String generateQrLink(Integer eserId) {
        return frontendBaseUrl + "/eser/" + eserId;
    }
}