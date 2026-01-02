package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "admin_users")
public class AdminUser extends BaseEntity<Integer> {

    @Field(name = "username")
    private String username;

    @Field(name = "password")
    private String password; // Şifreli olacak (bcrypt)

    @Field(name = "email")
    private String email;

    @Field(name = "is_active")
    private Boolean isActive = true;
}