package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.controller.admin;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.IletisimMesajiRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.OkunduDurumuRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.IletisimMesajiResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.IletisimMesajiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/iletisim")
@RequiredArgsConstructor
public class IletisimMesajiAdminController {
    private final IletisimMesajiService iletisimMesajiService;

    @GetMapping
    public ResponseEntity<List<IletisimMesajiResponseDTO>> getAllIletisimMesaji() {
        List<IletisimMesajiResponseDTO> iletisimList = iletisimMesajiService.getAllIletisimMesaji();
        return ResponseEntity.ok(iletisimList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IletisimMesajiResponseDTO> getIletisimMesajiById(@PathVariable Integer id) {
        IletisimMesajiResponseDTO iletisim = iletisimMesajiService.getIletisimMesajiById(id);
        return ResponseEntity.ok(iletisim);
    }

    //gereksinim senaryosu bulamadım silebiliriz
    @PutMapping("/{id}")
    public ResponseEntity<IletisimMesajiResponseDTO> updateIletisimMesaji(@PathVariable Integer id,
                                                                          @Valid @RequestBody IletisimMesajiRequestDTO request) {
        IletisimMesajiResponseDTO updated = iletisimMesajiService.updateIletisimMesaji(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIletisimMesaji(@PathVariable Integer id) {
        iletisimMesajiService.deleteIletisimMesaji(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/okundu")
    public ResponseEntity<IletisimMesajiResponseDTO> updateOkunduDurumu(
            @PathVariable Integer id,
            @RequestBody OkunduDurumuRequestDTO request) {
        IletisimMesajiResponseDTO updated = iletisimMesajiService.updateOkunduDurumu(id, request.okundu());
        return ResponseEntity.ok(updated);
    }
}
