package com.dijital_jeoloji_muze_backend.dijital_jeoloji_muze_backend.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

// Input: "https://example.com/eser/1"   -------> Output: PNG görseli (byte array) → Binary → Database'e kaydet
@Component
@Slf4j
public class QrCodeGenerator {

    public byte[] generateQrCode(String text, int width, int height) throws Exception {
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);

        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "PNG", outputStream);
        byte[] qrImageBytes = outputStream.toByteArray();

        return qrImageBytes; //  png  görsel (byte array)
    }

    public byte[] generateQrCodeDefault(String text) throws Exception {
        return generateQrCode(text, 300, 300);
    }
}