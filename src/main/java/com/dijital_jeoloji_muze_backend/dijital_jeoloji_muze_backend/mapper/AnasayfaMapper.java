package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.mapper;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.dto.response.AnasayfaResponseDTO;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.Anasayfa;
import org.bson.types.Binary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface AnasayfaMapper {

    @Mapping(target = "fotoData", source = "foto", qualifiedByName = "binaryToBase64")
    AnasayfaResponseDTO toAnasayfaResponseDTO(Anasayfa anasayfa);

    @Named("binaryToBase64")
    default String binaryToBase64(Binary foto) {
        if (foto == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(foto.getData());
    }
}