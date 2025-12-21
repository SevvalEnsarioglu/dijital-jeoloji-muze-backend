package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.AnasayfaResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.Anasayfa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnasayfaMapper {
    Anasayfa toAnasayfaEntity(Anasayfa anasayfa);
    AnasayfaResponseDTO toAnasayfaResponseDTO(Anasayfa anasayfa);

}
