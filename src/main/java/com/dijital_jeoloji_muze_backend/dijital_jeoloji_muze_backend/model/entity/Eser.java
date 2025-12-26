package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity;

import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "eser")
public class Eser extends BaseEntity<Integer> {
    @Field(name = "isim")
    private String isim;

    @Field(name = "qr_link")
    private String qrLink;

    @Field(name = "qr_foto")
    private Binary qrFoto; //qr kod görseli (qr link aracılığıyla oluşturulacak)

    @Field(name = "foto") //ileride burası çoklu olabilir
    private Binary foto;

    @Field(name= "ses")
    private Binary ses;

    @Field(name = "donem")
    private String donem;

    @Field(name= "boyut")
    private String boyut;

    @Field(name= "getiren_kisi")
    private String getirenKisi;

    @Field(name= "getirildigi_tarih")
    private LocalDate getirildigiTarih;

    @Field(name= "aciklama")
    private String aciklama;

    @Field(name= "goruntulenme_sayisi")
    @Indexed
    private Long goruntulenmeSayisi = 0L;
}
