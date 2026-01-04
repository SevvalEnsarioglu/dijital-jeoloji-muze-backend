package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.controller.admin;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.EserRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.EserResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.EserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/eser")
@RequiredArgsConstructor
public class EserAdminController {

    private final EserService eserService;

    @PostMapping
    public ResponseEntity<EserResponseDTO> createEser(@ModelAttribute @Valid EserRequestDTO request) {
        EserResponseDTO created = eserService.createEser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EserResponseDTO> updateEser(@PathVariable Integer id, @ModelAttribute @Valid EserRequestDTO request) {
        EserResponseDTO updated = eserService.updateEser(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEser(
            @PathVariable Integer id) {
        eserService.deleteEser(id);
        return ResponseEntity.noContent().build();
    }

}
