package com.inventory.util;

import org.springframework.stereotype.Component;

@Component
public class SkuGenerator {

    public String generateSku(String productName, Long count) {

        String prefix = productName
                .trim()
                .substring(0, Math.min(3, productName.length()))
                .toUpperCase();

        return prefix + String.format("%05d", count + 1);
    }

}