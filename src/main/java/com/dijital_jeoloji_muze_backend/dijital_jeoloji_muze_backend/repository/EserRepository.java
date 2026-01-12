package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.Eser;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EserRepository extends MongoRepository<Eser, Integer> {

    @Query(value = "{ 'isim': { $regex: ?0, $options: 'i' } }")
    List<Eser> findByIsimContainingIgnoreCase(String isim, Sort sort);

}
