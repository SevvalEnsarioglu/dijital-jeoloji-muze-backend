package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.repository;

import com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity.AdminUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRepository extends MongoRepository<AdminUser, Integer> {
    Optional<AdminUser> findByUsername(String username);
}