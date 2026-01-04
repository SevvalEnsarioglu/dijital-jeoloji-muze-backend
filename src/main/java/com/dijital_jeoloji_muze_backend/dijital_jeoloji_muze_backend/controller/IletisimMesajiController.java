package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.controller;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.IletisimMesajiRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.IletisimMesajiResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.IletisimMesajiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/iletisim")
@RequiredArgsConstructor
public class IletisimMesajiController {

    private final IletisimMesajiService iletisimMesajiService;

    @PostMapping
    public ResponseEntity<IletisimMesajiResponseDTO> createIletisimMesaji(@Valid @RequestBody IletisimMesajiRequestDTO request){
        IletisimMesajiResponseDTO created = iletisimMesajiService.createIletisimMesaji(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}
