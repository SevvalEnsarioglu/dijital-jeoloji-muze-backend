package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "bize_ulasin")
public class BizeUlasin extends BaseEntity {
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

    @Field(name = "mesajiniz")
    private String mesajiniz;

    @Field(name = "okundu")
    @Builder.Default
    private Boolean okundu = false;

}
