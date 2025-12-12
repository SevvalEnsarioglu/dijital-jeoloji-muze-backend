package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.IletisimMesajiRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.IletisimMesajiResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.IletisimMesaji;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IletisimMesajiMapper {
    IletisimMesaji toIletisimMesajiEntity(IletisimMesajiRequestDTO request);
    IletisimMesajiResponseDTO toIletisimMesajiResponseDTO(IletisimMesaji iletisimMesaji);
}
