package com.inventory.dto;

import java.math.BigDecimal;

public class ProductResponse {

    private Long id;

    private String name;

    private String sku;

    private BigDecimal sellingPrice;

    private BigDecimal costPrice;

    private String description;

    private String categoryName;

    public ProductResponse(){}

    public ProductResponse(Long id,String name,String sku,
                           BigDecimal sellingPrice,
                           BigDecimal costPrice,
                           String description,
                           String categoryName){

        this.id=id;
        this.name=name;
        this.sku=sku;
        this.sellingPrice=sellingPrice;
        this.costPrice=costPrice;
        this.description=description;
        this.categoryName=categoryName;
    }

    // getters setters
}