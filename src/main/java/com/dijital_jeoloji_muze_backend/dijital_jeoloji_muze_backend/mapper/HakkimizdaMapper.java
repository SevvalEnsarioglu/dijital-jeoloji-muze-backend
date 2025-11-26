package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.request.HakkimizdaRequestDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.HakkimizdaResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.Hakkimizda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HakkimizdaMapper {


    Hakkimizda toHakkimizdaEntity(HakkimizdaRequestDTO request);

    HakkimizdaResponseDTO toHakkimizdaResponseDTO(Hakkimizda hakkimizda);
}