package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.controller;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.EserResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.EserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eser")
@RequiredArgsConstructor
public class EserController {

    private final EserService eserService;

    @GetMapping
    public ResponseEntity<List<EserResponseDTO>> getAllEser(
            @RequestParam(required = false) String isim,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection
    ) {
        List<EserResponseDTO> eserList = eserService.getAllEser(isim, sortBy, sortDirection);
        return ResponseEntity.ok(eserList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EserResponseDTO> getEserById(@PathVariable Integer id) {
        EserResponseDTO eser = eserService.getEserById(id);
        return ResponseEntity.ok(eser);
    }

}