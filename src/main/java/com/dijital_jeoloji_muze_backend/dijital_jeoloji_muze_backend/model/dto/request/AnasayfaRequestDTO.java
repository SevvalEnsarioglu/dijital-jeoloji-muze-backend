package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record AnasayfaRequestDTO(
                String baslik,
                String aciklama,
                MultipartFile foto) {
}
