package com.inventory.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class QrCodeGenerator {

    private static final String QR_PATH =
            "src/main/resources/static/qrcode/";

    public String generateQrCode(String fileName, String data) {

        try {

            Files.createDirectories(Paths.get(QR_PATH));

            String safeFileName = fileName.replaceAll("[^a-zA-Z0-9_-]", "_");

            Path path = Paths.get(QR_PATH + safeFileName + ".png");

            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                    data,
                    BarcodeFormat.QR_CODE,
                    300,
                    300
            );

            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            return "/qrcode/" + safeFileName + ".png";

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate QR Code", e);
        }
    }
}