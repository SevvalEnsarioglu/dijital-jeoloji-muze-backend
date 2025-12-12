package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.service;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.DatabaseSequence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Slf4j
@Service
@RequiredArgsConstructor
public class SequenceGeneratorService {

    private final MongoOperations mongoOperations;
    public long generateSequence(String sequenceName) {
        DatabaseSequence counter = mongoOperations.findAndModify(
                query(where("_id").is(sequenceName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                DatabaseSequence.class
        );

        // upsert(true) kullanıldığı için counter her zaman null olmayacaktır ancak yine de güvenlik kontrolü yapıyoruz ve hata durumunda log kaydı tutuyoruz
        if (counter == null) {
            log.error("Sequence oluşturulamadı veya bulunamadı: {}", sequenceName);
            throw new IllegalStateException(
                    "Sequence oluşturulamadı: " + sequenceName);
        }
        return counter.getSeq();
    }
}