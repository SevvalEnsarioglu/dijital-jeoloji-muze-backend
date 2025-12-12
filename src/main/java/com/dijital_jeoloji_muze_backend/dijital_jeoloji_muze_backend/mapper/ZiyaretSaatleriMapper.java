package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.ZiyaretSaatleriRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.ZiyaretSaatleriResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.ZiyaretSaatleri;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ZiyaretSaatleriMapper {

    ZiyaretSaatleri toEntity(ZiyaretSaatleriRequestDTO request);

    ZiyaretSaatleriResponseDTO toResponseDTO(ZiyaretSaatleri entity);
}
