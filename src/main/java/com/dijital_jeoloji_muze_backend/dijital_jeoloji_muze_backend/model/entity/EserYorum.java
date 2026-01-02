package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "eser_yorum")
public class EserYorum extends BaseEntity<Integer> {

    @Field(name = "eser_id")
    @Indexed
    private String eserID;

    @Field(name="ad_soyad")
    private String adSoyad;

    @Field(name="email")
    private String email;

    @Field(name="yorum")
    private String yorum;

    @Field(name="puan")
    @Indexed
    @Min(value = 1, message = "Puan en az 1 olabilir")
    @Max(value = 5, message = "Puan en fazla 5 olabilir")
    private Integer puan;

    //buraya okundu ekleme sebebimiz admin hangi yorumların görüneceğini belirleyebilsin diye
    @Field(name = "okundu")
    private Boolean okundu = true;

}
