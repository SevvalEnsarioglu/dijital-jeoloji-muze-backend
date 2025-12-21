package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.controller;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.AnasayfaResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.AnasayfaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/anasayfa")
@RequiredArgsConstructor
public class AnasayfaController {

    private final AnasayfaService anasayfaService;

    @PostMapping("/upload")
    public ResponseEntity<AnasayfaResponseDTO> createAnasayfa(
            @RequestParam("aciklama") String aciklama,
            @RequestParam("foto") MultipartFile foto) {
        AnasayfaResponseDTO created = anasayfaService.createAnasayfa(aciklama, foto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<AnasayfaResponseDTO>> getAllAnasayfa() {
        List<AnasayfaResponseDTO> anasayfaList = anasayfaService.getAllAnasayfa();
        return ResponseEntity.ok(anasayfaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnasayfaResponseDTO> getAnasayfaById(@PathVariable Integer id) {
        AnasayfaResponseDTO anasayfa = anasayfaService.getAnasayfaById(id);
        return ResponseEntity.ok(anasayfa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnasayfaResponseDTO> updateAnasayfa(
            @PathVariable Integer id,
            @RequestParam("aciklama") String aciklama,
            @RequestParam(value = "foto", required = false) MultipartFile foto) {
        AnasayfaResponseDTO updated = anasayfaService.updateAnasayfa(id, aciklama, foto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnasayfa(@PathVariable Integer id) {
        anasayfaService.deleteAnasayfa(id);
        return ResponseEntity.noContent().build();
    }


}