package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.controller;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.EserYorumRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.EserYorumResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.EserYorumService;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.OkunduDurumuRequestDTO;
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

    //var olan tüm yorumlar
    @GetMapping
    public ResponseEntity<List<EserYorumResponseDTO>> getAllEserYorum() {
        List<EserYorumResponseDTO> yorumList = eserYorumService.getAllEserYorum();
        return ResponseEntity.ok(yorumList);
    }

    //ilgili yorum id si ile tek bir yorum
    @GetMapping("/{id}")
    public ResponseEntity<EserYorumResponseDTO> getEserYorumById(@PathVariable Integer id) {
        EserYorumResponseDTO yorum = eserYorumService.getEserYorumById(id);
        return ResponseEntity.ok(yorum);
    }

    //ilgili esere ait yorumlar
    @GetMapping("/eser/{eserID}")
    public ResponseEntity<List<EserYorumResponseDTO>> getYorumByEserId(@PathVariable Integer eserID) {
        List<EserYorumResponseDTO> yorumList = eserYorumService.getYorumByEserId(eserID);
        return ResponseEntity.ok(yorumList);
    }

    // eser yorum güncelleme (admin isterse yani ama sebebini gereksinimini düşünmedim)
    @PutMapping("/{id}")
    public ResponseEntity<EserYorumResponseDTO> updateEserYorum(@PathVariable Integer id, @Valid @RequestBody EserYorumRequestDTO request) {
        EserYorumResponseDTO updated = eserYorumService.updateEserYorum(id, request);
        return ResponseEntity.ok(updated);
    }

    //yorum okundu durumu güncelleme
    @PatchMapping("/{id}/okundu")
    public ResponseEntity<EserYorumResponseDTO> updateOkunduDurumu(@PathVariable Integer id, @RequestBody OkunduDurumuRequestDTO request) {
        EserYorumResponseDTO updated = eserYorumService.updateOkunduDurumu(id, request.okundu());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEserYorum(@PathVariable Integer id) {
        eserYorumService.deleteEserYorum(id);
        return ResponseEntity.noContent().build();
    }
}