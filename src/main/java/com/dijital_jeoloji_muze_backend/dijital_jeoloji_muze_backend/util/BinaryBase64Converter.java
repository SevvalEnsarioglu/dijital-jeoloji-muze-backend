package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.util;

import org.bson.types.Binary;
import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

@Component
public class BinaryBase64Converter {

    // Popüler dosya imzaları (Magic Bytes) haritası
    private static final TreeMap<String, String> SIGNATURES = new TreeMap<>();

    static {
        SIGNATURES.put("ID3", "audio/mpeg");      // MP3
        SIGNATURES.put("\u00FF\u00D8\u00FF", "image/jpeg"); // JPEG
        SIGNATURES.put("\u0089PNG", "image/png");  // PNG
        SIGNATURES.put("GIF8", "image/gif");     // GIF
        SIGNATURES.put("%PDF", "application/pdf"); // PDF
        SIGNATURES.put("RIFF", "audio/wav");      // WAV
    }

    /**
     * Binary veriyi analiz eder ve doğru MIME tipi ile Base64 Data URI döndürür.
     */
    public String binaryToBase64(Binary binary) {
        if (binary == null || binary.getData() == null) {
            return null;
        }

        byte[] data = binary.getData();
        String base64String = Base64.getEncoder().encodeToString(data);
        String mimeType = detectMimeType(data);

        return "data:" + mimeType + ";base64," + base64String;
    }

    /**
     * Base64 formatındaki veriyi (Header olsa bile) temizleyip Binary'e çevirir.
     */
    public Binary base64ToBinary(String base64String) {
        if (base64String == null || base64String.isBlank()) {
            return null;
        }

        // Header varsa temizle (Örn: data:image/jpeg;base64,...)
        String pureBase64 = base64String.contains(",")
                ? base64String.substring(base64String.indexOf(",") + 1)
                : base64String;

        try {
            return new Binary(Base64.getDecoder().decode(pureBase64.trim()));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Dosyanın ilk baytlarını okuyarak gerçek MIME tipini döner.
     */
    private String detectMimeType(byte[] data) {
        if (data.length < 4) return "application/octet-stream";

        // İlk 4 baytı String olarak al (Dosya imzası kontrolü için)
        String header = new String(data, 0, Math.min(data.length, 4), java.nio.charset.StandardCharsets.ISO_8859_1);

        for (Map.Entry<String, String> entry : SIGNATURES.entrySet()) {
            if (header.startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }

        // Özel durum: Bazı MP3'ler ID3 ile başlamayabilir, doğrudan frame ile başlayabilir (FF FB)
        if (data[0] == (byte) 0xFF && (data[1] == (byte) 0xFB || data[1] == (byte) 0xF3 || data[1] == (byte) 0xF2)) {
            return "audio/mpeg";
        }

        return "application/octet-stream";
    }
}