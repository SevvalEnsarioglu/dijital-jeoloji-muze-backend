package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity;

import lombok.*;
import org.bson.types.ObjectId;
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

    @Field(name = "qr_id")
    private ObjectId qrId;  // görselin GridFS id'si

    @Field(name = "foto_id")
    private ObjectId fotoId;

    @Field(name= "ses_id")
    private ObjectId sesId;

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
    private Long goruntulenmeSayisi;

}
