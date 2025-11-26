package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.BaseEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "hakkimizda")
public class Hakkimizda extends BaseEntity {
    @Field(name = "hakkinda")
    private String hakkinda;

    @Field(name="adres")
    private String adres;

    @Field(name = "telefon")
    private String telefon;

    @Field(name = "email")
    private String email;

}
