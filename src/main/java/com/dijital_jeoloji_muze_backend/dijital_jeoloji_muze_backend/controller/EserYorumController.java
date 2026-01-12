package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.controller;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.EserYorumRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.EserYorumResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.EserYorumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eser-yorum")
@RequiredArgsConstructor
public class EserYorumController {

    private final EserYorumService eserYorumService;

    @PostMapping
    public ResponseEntity<EserYorumResponseDTO> createEserYorum(@Valid @RequestBody EserYorumRequestDTO request) {
        EserYorumResponseDTO created = eserYorumService.createEserYorum(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //ilgili esere ait yorumlar
    @GetMapping("/eser/{eserID}")
    public ResponseEntity<List<EserYorumResponseDTO>> getYorumByEserId(
            @PathVariable Integer eserID,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection
    ) {
        List<EserYorumResponseDTO> yorumList = eserYorumService.getYorumByEserId(eserID, sortBy, sortDirection);
        return ResponseEntity.ok(yorumList);
    }

}