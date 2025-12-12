package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.DatabaseSequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatabaseSequenceRepository extends MongoRepository<DatabaseSequence, String> {
}