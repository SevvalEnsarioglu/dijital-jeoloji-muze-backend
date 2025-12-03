package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.controller;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.IletisimRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.IletisimResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.IletisimService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/iletisim")
@RequiredArgsConstructor
public class IletisimController {

    private final IletisimService iletisimService;

    @PostMapping
    public ResponseEntity<IletisimResponseDTO> createIletisim(@Valid @RequestBody IletisimRequestDTO request){
        IletisimResponseDTO created = iletisimService.createIletisim(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<IletisimResponseDTO>> getAllIletisim() {
        List<IletisimResponseDTO> iletisimList = iletisimService.getAllIletisim();
        return ResponseEntity.ok(iletisimList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IletisimResponseDTO> getIletisimById(@PathVariable String id) {
        IletisimResponseDTO iletisim = iletisimService.getIletisimById(id);
        return ResponseEntity.ok(iletisim);
    }

    //gereksinim senaryosu bulamadım silebiliriz.
    @PutMapping("/{id}")
    public ResponseEntity<IletisimResponseDTO> updateIletisim(@PathVariable String id,
                                                              @Valid @RequestBody IletisimRequestDTO request) {
        IletisimResponseDTO updated = iletisimService.updateIletisim(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIletisim(@PathVariable String id) {
        iletisimService.deleteIletisim(id);
        return ResponseEntity.noContent().build();
    }

}
