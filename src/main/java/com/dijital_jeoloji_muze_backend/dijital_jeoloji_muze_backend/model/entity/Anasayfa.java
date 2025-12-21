package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "anasayfa")
public class Anasayfa extends BaseEntity<Integer> {

    @Field(name = "foto")
    private Binary foto;

    @Field(name = "foto_isim")
    private String foto_isim;

    @Field(name= "aciklama")
    private String aciklama;
}
