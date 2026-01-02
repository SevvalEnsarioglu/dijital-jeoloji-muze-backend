package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.EserYorum;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EserYorumRepository extends MongoRepository<EserYorum, Integer> {
    List<EserYorum> findByEserID(Integer eserID, Sort sort);

}
