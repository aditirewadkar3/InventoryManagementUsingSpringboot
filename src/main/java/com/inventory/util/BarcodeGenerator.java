package com.inventory.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class BarcodeGenerator {

    private static final String BARCODE_PATH =
            "src/main/resources/static/barcode/";

    public String generateBarcode(String sku) {

        try {

            Files.createDirectories(Paths.get(BARCODE_PATH));

            String fileName = sku + ".png";

            Path path = Paths.get(BARCODE_PATH + fileName);

            BitMatrix matrix =
                    new MultiFormatWriter().encode(
                            sku,
                            BarcodeFormat.CODE_128,
                            300,
                            100
                    );

            MatrixToImageWriter.writeToPath(matrix, "PNG", path);

            return "/barcode/" + fileName;

        } catch (Exception e) {

            throw new RuntimeException("Unable to generate barcode");

        }

    }

}