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
    @Field(name = "isim")
    private String isim;

    @Field(name = "foto")
    private Binary foto;

    @Field(name= "aciklama")
    private String aciklama;
}
