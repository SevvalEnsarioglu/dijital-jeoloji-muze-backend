package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.controller.admin;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.AnasayfaRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.AnasayfaResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.AnasayfaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/anasayfa")
@RequiredArgsConstructor
public class AnasayfaAdminController {

    private final AnasayfaService anasayfaService;

    @GetMapping("/{id}")
    public ResponseEntity<AnasayfaResponseDTO> getAnasayfaById(
            @PathVariable Integer id) {
        AnasayfaResponseDTO anasayfa = anasayfaService.getAnasayfaById(id);
        return ResponseEntity.ok(anasayfa);
    }

    @PostMapping
    public ResponseEntity<AnasayfaResponseDTO> createAnasayfa(
            @ModelAttribute @Valid AnasayfaRequestDTO request) {
        AnasayfaResponseDTO created = anasayfaService.createAnasayfa(
                request.baslik(),
                request.aciklama(),
                request.foto());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnasayfaResponseDTO> updateAnasayfa(
            @PathVariable Integer id,
            @ModelAttribute @Valid AnasayfaRequestDTO request) {
        AnasayfaResponseDTO updated = anasayfaService.updateAnasayfa(
                id,
                request.baslik(),
                request.aciklama(),
                request.foto());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnasayfa(
            @PathVariable Integer id) {
        anasayfaService.deleteAnasayfa(id);
        return ResponseEntity.noContent().build();
    }

}
