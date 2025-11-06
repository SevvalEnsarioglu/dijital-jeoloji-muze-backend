package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.entity;

import org.springframework.data.mongodb.core.mapping.Field;

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
