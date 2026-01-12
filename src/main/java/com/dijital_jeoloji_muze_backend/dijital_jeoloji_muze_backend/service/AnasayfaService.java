package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.AnasayfaResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnasayfaService {

    AnasayfaResponseDTO createAnasayfa(String baslik, String aciklama, MultipartFile foto);
    
    AnasayfaResponseDTO getAnasayfaById(Integer id);

    AnasayfaResponseDTO updateAnasayfa(Integer id, String baslik, String aciklama, MultipartFile foto);

    void deleteAnasayfa(Integer id);

    List<AnasayfaResponseDTO> getAllAnasayfa(String sortBy, String sortDirection);
}
