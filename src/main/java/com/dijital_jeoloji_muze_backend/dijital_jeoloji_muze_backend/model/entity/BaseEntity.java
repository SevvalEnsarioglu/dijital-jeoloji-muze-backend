package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity implements Persistable<String> {
    @Id
    private String id;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
    @Override
    public boolean isNew() {
        // Eğer createdAt henüz oluşmamışsa veya ID yoksa, bu yenidir diyoruz, singleton id için
        return createdAt == null || id == null;
    }
}