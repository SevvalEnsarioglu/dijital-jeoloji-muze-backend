package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.EserResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.Eser;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.util.BinaryBase64Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class EserMapper {
    @Autowired
    protected BinaryBase64Converter converter;

    @Mapping(target = "qrFoto", expression = "java(converter.binaryToBase64(eser.getQrFoto()))")
    @Mapping(target = "foto", expression = "java(converter.binaryToBase64(eser.getFoto()))")
    @Mapping(target = "ses", expression = "java(converter.binaryToBase64(eser.getSes()))")
    public abstract EserResponseDTO toEserResponseDTO(Eser eser);

}
