package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.util;

import org.bson.types.Binary;
import org.springframework.stereotype.Component;
import java.util.Base64;

@Component
public class BinaryBase64Converter {

    public String binaryToBase64(Binary binary) {
        if (binary == null || binary.getData() == null) {
            return null;
        }

        byte[] data = binary.getData();
        String base64String = Base64.getEncoder().encodeToString(data);
        String mimeType = detectMimeType(data);

        return "data:" + mimeType + ";base64," + base64String;
    }

    public Binary base64ToBinary(String base64String) {
        if (base64String == null || base64String.isBlank()) {
            return null;
        }

        String pureBase64 = base64String.contains(",")
                ? base64String.substring(base64String.indexOf(",") + 1)
                : base64String;

        try {
            return new Binary(Base64.getDecoder().decode(pureBase64.trim()));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private String detectMimeType(byte[] data) {
        if (data == null || data.length < 4) return "application/octet-stream";
        if (data[0] == (byte) 0xFF && data[1] == (byte) 0xD8 && data[2] == (byte) 0xFF) {
            return "image/jpeg";
        }
        if (data[0] == (byte) 0x89 && data[1] == (byte) 0x50 && data[2] == (byte) 0x4E && data[3] == (byte) 0x47) {
            return "image/png";
        }
        if (data[0] == (byte) 0x49 && data[1] == (byte) 0x44 && data[2] == (byte) 0x33) {
            return "audio/mpeg";
        }
        if (data[0] == (byte) 0xFF && (data[1] == (byte) 0xFB || data[1] == (byte) 0xF3 || data[1] == (byte) 0xF2)) {
            return "audio/mpeg";
        }
        if (data[0] == (byte) 0x47 && data[1] == (byte) 0x49 && data[2] == (byte) 0x46 && data[3] == (byte) 0x38) {
            return "image/gif";
        }
        if (data[0] == (byte) 0x25 && data[1] == (byte) 0x50 && data[2] == (byte) 0x44 && data[3] == (byte) 0x46) {
            return "application/pdf";
        }
        if (data[0] == (byte) 0x52 && data[1] == (byte) 0x49 && data[2] == (byte) 0x46 && data[3] == (byte) 0x46) {
            return "audio/wav";
        }

        return "application/octet-stream";
    }
}