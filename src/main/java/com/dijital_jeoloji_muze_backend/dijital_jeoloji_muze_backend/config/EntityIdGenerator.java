package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.config;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.BaseEntity;
import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ResolvableType;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Slf4j
@Component
@RequiredArgsConstructor
public class EntityIdGenerator extends AbstractMongoEventListener<BaseEntity<?>> {
    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<BaseEntity<?>> event) {
        BaseEntity<?> entity = event.getSource();

        if (entity.getId() != null) {         // id null değilse işlem yapma
            return;
        }
        if (!isIntegerIdEntity(entity)) {        // Generic type kontrolü sadece Integer id'li entity'ler için çalış
            return;
        }

        String collectionName = event.getCollectionName();
        long nextId = sequenceGenerator.generateSequence(collectionName);
        setIdSafely(entity, (int) nextId);
    }

    private void setIdSafely(BaseEntity<?> entity, Integer id) {
        try {
            // Reflection ile setId metodunu bul ve çağır
            Method setIdMethod = entity.getClass().getMethod("setId", Object.class);
            setIdMethod.invoke(entity, id);
        } catch (Exception e) {
            log.error("Entity ID set edilirken hata oluştu. Entity: {}, ID: {}",
                    entity.getClass().getSimpleName(), id, e);
            throw new IllegalStateException(
                    "Entity ID set edilemedi: " + entity.getClass().getSimpleName(), e);
        }
    }

    private boolean isIntegerIdEntity(BaseEntity<?> entity) {
        try {
            Class<?> entityClass = entity.getClass();

            Type superclass = entityClass.getGenericSuperclass();

            if (superclass instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) superclass;
                Type[] typeArguments = parameterizedType.getActualTypeArguments();

                if (typeArguments.length > 0) {
                    Type idType = typeArguments[0];

                    if (idType == Integer.class || idType == int.class) {
                        return true;
                    }

                    if (idType instanceof Class) {
                        Class<?> idClass = (Class<?>) idType;
                        return idClass == Integer.class || idClass == int.class;
                    }
                }
            }

            ResolvableType resolvableType = ResolvableType.forClass(entityClass)
                    .as(BaseEntity.class);

            if (resolvableType.hasGenerics()) {
                Class<?> idClass = resolvableType.getGeneric(0).resolve();
                return idClass == Integer.class || idClass == int.class;
            }

        } catch (Exception e) {
            log.warn("Entity ID tipi kontrol edilirken hata oluştu: {}",
                    entity.getClass().getSimpleName(), e);
            return false;
        }

        return false;
    }
}