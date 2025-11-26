package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "ziyaret_saatleri")
public class ZiyaretSaatleri extends BaseEntity {

    @Field(name = "gun")
    private String gun;

    @Field(name = "acilis_saati")
    private LocalTime acilisSaati;

    @Field(name = "kapanis_saati")
    private LocalTime kapanisSaati;
}
