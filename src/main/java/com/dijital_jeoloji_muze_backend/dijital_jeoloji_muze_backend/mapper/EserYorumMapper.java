package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.EserRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.EserYorumResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.EserYorum;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EserYorumMapper {
    EserYorum toEntity(EserRequestDTO request);
    EserYorumResponseDTO toResponseDTO(EserYorum entity);
}
