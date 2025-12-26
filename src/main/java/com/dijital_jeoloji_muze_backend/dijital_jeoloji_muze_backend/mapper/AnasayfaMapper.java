package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.AnasayfaResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.Anasayfa;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.util.BinaryBase64Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AnasayfaMapper {

    @Autowired
    protected BinaryBase64Converter converter;

    @Mapping(target = "fotoData", expression = "java(converter.binaryToBase64(anasayfa.getFoto()))")
    public abstract AnasayfaResponseDTO toAnasayfaResponseDTO(Anasayfa anasayfa);
}