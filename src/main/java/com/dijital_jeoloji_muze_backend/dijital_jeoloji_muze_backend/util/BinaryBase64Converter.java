package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.util;

import org.bson.types.Binary;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class BinaryBase64Converter {

    public String binaryToBase64(Binary binary) {
        if (binary == null) {
            return null;
        }
        String base64String = Base64.getEncoder().encodeToString(binary.getData());
        // Data URI formatında döndür değiştirildi
        return "data:image/jpeg;base64," + base64String;
    }

    public Binary base64ToBinary(String base64String) {
        if (base64String == null || base64String.isBlank()) {
            return null;
        }
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        return new Binary(decodedBytes);
    }
}