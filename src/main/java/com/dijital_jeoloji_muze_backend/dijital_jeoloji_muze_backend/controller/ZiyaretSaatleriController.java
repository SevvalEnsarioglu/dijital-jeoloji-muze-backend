package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.controller;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.ZiyaretSaatleriResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.ZiyaretSaatleriService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ziyaret-saatleri")
@RequiredArgsConstructor
public class ZiyaretSaatleriController {

    private final ZiyaretSaatleriService ziyaretSaatleriService;

    @GetMapping
    public ResponseEntity<List<ZiyaretSaatleriResponseDTO>> getAllZiyaretSaatleri(){
        List<ZiyaretSaatleriResponseDTO> ziyaretSaatleriList = ziyaretSaatleriService.getAllZiyaretSaatleri();
        return ResponseEntity.ok(ziyaretSaatleriList);
    }

}





