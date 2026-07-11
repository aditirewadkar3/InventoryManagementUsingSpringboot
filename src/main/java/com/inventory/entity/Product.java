package com.inventory.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,length=100)
    private String name;

    @Column(nullable=false,unique=true,length=30)
    private String sku;

    @Column(nullable=false)
    private BigDecimal sellingPrice;

    @Column(nullable=false)
    private BigDecimal costPrice;

    @Column(length=255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id",nullable=false)
    private Category category;

    public Product(){}

    public Long getId(){ return id; }

    public String getName(){ return name; }

    public void setName(String name){ this.name=name; }

    public String getSku(){ return sku; }

    public void setSku(String sku){ this.sku=sku; }

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

    public Category getCategory(){ return category; }

    public void setCategory(Category category){
        this.category=category;
    }
}