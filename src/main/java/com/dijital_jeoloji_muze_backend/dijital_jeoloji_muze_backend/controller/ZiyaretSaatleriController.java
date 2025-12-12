package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.controller;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.ZiyaretSaatleriRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.ZiyaretSaatleriResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.ZiyaretSaatleriService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ziyaret-saatleri")
@RequiredArgsConstructor
public class ZiyaretSaatleriController {

    private final ZiyaretSaatleriService ziyaretSaatleriService;

    @PostMapping
    public ResponseEntity<ZiyaretSaatleriResponseDTO> createZiyaretSaatleri(@Valid @RequestBody ZiyaretSaatleriRequestDTO request){
        ZiyaretSaatleriResponseDTO created = ziyaretSaatleriService.createZiyaretSaatleri(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ZiyaretSaatleriResponseDTO>> getAllZiyaretSaatleri(){
        List<ZiyaretSaatleriResponseDTO> ziyaretSaatleriList = ziyaretSaatleriService.getAllZiyaretSaatleri();
        return ResponseEntity.ok(ziyaretSaatleriList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZiyaretSaatleriResponseDTO> updateZiyaretSaatleri(@PathVariable Integer id,
                                                                            @Valid @RequestBody ZiyaretSaatleriRequestDTO request){
        ZiyaretSaatleriResponseDTO updated = ziyaretSaatleriService.updateZiyaretSaatleri(id, request);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZiyaretSaatleri(@PathVariable Integer id) {
        ziyaretSaatleriService.deleteZiyaretSaatleri(id);
        return ResponseEntity.noContent().build();
    }
}





