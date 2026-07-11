package com.inventory.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private String sku;

    private BigDecimal sellingPrice;

    private BigDecimal costPrice;

    private String description;

    private String barcode;

    private String qrCode;

    private String categoryName;

}