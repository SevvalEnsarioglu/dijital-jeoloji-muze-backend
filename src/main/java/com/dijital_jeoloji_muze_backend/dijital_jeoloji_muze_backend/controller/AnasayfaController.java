package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.controller;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.AnasayfaResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.AnasayfaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anasayfa")
@RequiredArgsConstructor
public class AnasayfaController {

    private final AnasayfaService anasayfaService;

    @GetMapping
    public ResponseEntity<List<AnasayfaResponseDTO>> getAllAnasayfa() {
        List<AnasayfaResponseDTO> anasayfaList = anasayfaService.getAllAnasayfa();
        return ResponseEntity.ok(anasayfaList);
    }

}