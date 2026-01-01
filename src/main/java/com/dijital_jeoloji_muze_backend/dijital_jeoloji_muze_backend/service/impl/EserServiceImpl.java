package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.impl;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper.EserMapper;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.EserRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.EserResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.Eser;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository.EserRepository;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.EserService;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.util.QrCodeGenerator;
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

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    private static final String[] ALLOWED_IMAGE_TYPES = { "image/jpeg", "image/png", "image/gif", "image/webp" };
    private static final String[] ALLOWED_AUDIO_TYPES = { "audio/mpeg", "audio/wav", "audio/ogg", "audio/webm" };

    @Override
    @Transactional
    public EserResponseDTO createEser(EserRequestDTO request) {
        validateFoto(request.foto());
        validateSes(request.ses());

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
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Dosya yükleme başarısız: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "QR kod oluşturma başarısız: " + e.getMessage());
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
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Eser bulunamadı. ID: " + id);
                });
        eser.setGoruntulenmeSayisi(eser.getGoruntulenmeSayisi() + 1);
        eserRepository.save(eser);
        return eserMapper.toEserResponseDTO(eser);
    }

    @Override
    @Transactional
    public EserResponseDTO updateEser(Integer id, EserRequestDTO request) {
        Eser existing = eserRepository.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Eser bulunamadı. ID: " + id);
                });

        try {
            if (request.isim() != null && !request.isim().isBlank()) {
                existing.setIsim(request.isim());
            }
            if (request.donem() != null && !request.donem().isBlank()) {
                existing.setDonem(request.donem());
            }
            if (request.boyut() != null && !request.boyut().isBlank()) {
                existing.setBoyut(request.boyut());
            }
            if (request.getirenKisi() != null && !request.getirenKisi().isBlank()) {
                existing.setGetirenKisi(request.getirenKisi());
            }
            if (request.getirildigiTarih() != null) {
                existing.setGetirildigiTarih(request.getirildigiTarih());
            }
            if (request.aciklama() != null && !request.aciklama().isBlank()) {
                existing.setAciklama(request.aciklama());
            }
            if (request.foto() != null && !request.foto().isEmpty()) {
                validateFoto(request.foto());
                byte[] fotoBytes = request.foto().getBytes();
                existing.setFoto(new Binary(fotoBytes));
            }
            if (request.ses() != null && !request.ses().isEmpty()) {
                validateSes(request.ses());
                byte[] sesBytes = request.ses().getBytes();
                existing.setSes(new Binary(sesBytes));
            }

            Eser updated = eserRepository.save(existing);
            return eserMapper.toEserResponseDTO(updated);

        } catch (IOException e) {
            log.error("Dosya yükleme başarısız: {}", e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Dosya yükleme başarısız: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteEser(Integer id) {
        if (!eserRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Eser bulunamadı. ID: " + id);
        }

        eserRepository.deleteById(id);
    }

    private void validateFoto(MultipartFile foto) {
        if (foto == null || foto.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Foto dosyası boş olamaz");
        }
        if (foto.getSize() > MAX_FILE_SIZE) {
            log.warn("Foto çok büyük. Boyut: {} bytes", foto.getSize());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Foto boyutu 5MB'den küçük olmalıdır");
        }

        String contentType = foto.getContentType();
        if (contentType == null || !Arrays.asList(ALLOWED_IMAGE_TYPES).contains(contentType)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Sadece JPEG, PNG, GIF ve WebP formatları desteklenir");
        }
    }

    private void validateSes(MultipartFile ses) {
        if (ses == null || ses.isEmpty()) {
            return;
        }
        if (ses.getSize() > MAX_FILE_SIZE) {
            log.warn("Ses dosyası çok büyük. Boyut: {} bytes", ses.getSize());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ses dosyası boyutu 5MB'den küçük olmalıdır");
        }

        String contentType = ses.getContentType();
        if (contentType == null || !Arrays.asList(ALLOWED_AUDIO_TYPES).contains(contentType)) {
            log.warn("Desteklenmeyen ses formatı: {}", contentType);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Sadece MP3, WAV, OGG ve WebM formatları desteklenir");
        }
    }

    private String generateQrLink(Integer eserId) {
        return "https://your-domain.com/eser/" + eserId;
    }
}