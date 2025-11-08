package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.entity;

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
