package com.inventory.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProductRequest {

    @NotBlank
    private String name;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal sellingPrice;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal costPrice;

    private String description;

    @NotNull
    private Long categoryId;

    public ProductRequest(){}

    public String getName(){ return name; }

    public void setName(String name){ this.name=name; }

    public BigDecimal getSellingPrice(){ return sellingPrice; }

    public void setSellingPrice(BigDecimal sellingPrice){
        this.sellingPrice=sellingPrice;
    }

    public BigDecimal getCostPrice(){ return costPrice; }

    public void setCostPrice(BigDecimal costPrice){
        this.costPrice=costPrice;
    }

    public String getDescription(){ return description; }

    public void setDescription(String description){
        this.description=description;
    }

    public Long getCategoryId(){ return categoryId; }

    public void setCategoryId(Long categoryId){
        this.categoryId=categoryId;
    }
}