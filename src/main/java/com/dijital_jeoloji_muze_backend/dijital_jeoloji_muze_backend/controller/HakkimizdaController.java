package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.controller;


import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.HakkimizdaRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.HakkimizdaResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.HakkimizdaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.Map;

@RestController
@RequestMapping("/api/hakkimizda")
public class HakkimizdaController {

    private final HakkimizdaService hakkimizdaService;
    public HakkimizdaController(HakkimizdaService hakkimizdaService) {
        this.hakkimizdaService = hakkimizdaService;
    }
    @PostMapping
    public ResponseEntity<HakkimizdaResponseDTO> createHakkimizda(@Valid @RequestBody HakkimizdaRequestDTO request) {
        HakkimizdaResponseDTO created = hakkimizdaService.createHakkimizda(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<HakkimizdaResponseDTO> getHakkimizda(){
        HakkimizdaResponseDTO response = hakkimizdaService.getHakkimizda();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<HakkimizdaResponseDTO> updateHakkimizda(@Valid @RequestBody HakkimizdaRequestDTO request) {
        HakkimizdaResponseDTO updated = hakkimizdaService.updateHakkimizda(request);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping
    public ResponseEntity<HakkimizdaResponseDTO> patchHakkimizda(@RequestBody Map<String, Object> updates) {
        HakkimizdaResponseDTO updated = hakkimizdaService.patchHakkimizda(updates);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteHakkimizda() {
        hakkimizdaService.deleteHakkimizda();
        return ResponseEntity.noContent().build();
    }

}
