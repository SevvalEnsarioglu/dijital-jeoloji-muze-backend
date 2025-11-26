package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.Eser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EserRepository extends MongoRepository<Eser, ObjectId> {

}
