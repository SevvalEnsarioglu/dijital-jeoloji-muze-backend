package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.IletisimRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.HakkimizdaResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.Iletisim;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IletisimMapper {
    Iletisim toIletisimEntity(IletisimRequestDTO dto);
    HakkimizdaResponseDTO toHakkimizdaResponseDTO(Iletisim entity);
}
