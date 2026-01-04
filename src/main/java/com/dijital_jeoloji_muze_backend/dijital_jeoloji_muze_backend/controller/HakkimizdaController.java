package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.controller;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.HakkimizdaResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.HakkimizdaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hakkimizda")
@RequiredArgsConstructor
public class HakkimizdaController {

    private final HakkimizdaService hakkimizdaService;

    @GetMapping
    public ResponseEntity<HakkimizdaResponseDTO> getHakkimizda(){
        HakkimizdaResponseDTO response = hakkimizdaService.getHakkimizda();
        return ResponseEntity.ok(response);
    }

}
