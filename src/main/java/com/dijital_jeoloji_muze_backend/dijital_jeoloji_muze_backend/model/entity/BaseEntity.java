package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class BaseEntity<T> implements Persistable<T> {

    @Id
    protected T id;  // generic type parameter

    @CreatedDate
    @Indexed(direction = IndexDirection.DESCENDING)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @Override
    public T getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        // Eğer createdAt henüz oluşmamışsa veya ID yoksa, bu yenidir diyoruz, singleton id için
        return createdAt == null || id == null;
    }
}