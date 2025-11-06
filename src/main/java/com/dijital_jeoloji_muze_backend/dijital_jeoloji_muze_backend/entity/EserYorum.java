package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "eser_yorum")
public class EserYorum extends BaseEntity {

    @Field(name="ad_soyad")
    private String AdSoyad;

    @Field(name="yorum")
    private String Yorum;

    @Field(name="puan")
    private Integer puan;

}
