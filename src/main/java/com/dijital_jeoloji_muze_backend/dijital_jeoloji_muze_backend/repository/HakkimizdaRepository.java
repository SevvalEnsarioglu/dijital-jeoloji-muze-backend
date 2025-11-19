package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.entity.Hakkimizda;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HakkimizdaRepository extends MongoRepository<Hakkimizda, ObjectId> {
}
