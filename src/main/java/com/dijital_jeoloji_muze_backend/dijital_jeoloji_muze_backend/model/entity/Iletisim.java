package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "iletisim")
public class Iletisim extends BaseEntity {

    @Field(name = "ad")
    private String ad;

    @Field(name="soyad")
    private String soyad;

    @Field(name = "email")
    private String email;

    @Field(name = "konu")
    private String konu;

    @Field(name = "telefon")
    private String telefon;

    @Field(name = "mesaj")
    private String mesaj;

}
