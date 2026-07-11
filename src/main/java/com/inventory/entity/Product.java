package com.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,unique = true,length = 30)
    private String sku;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal sellingPrice;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal costPrice;

    @Column(length = 500)
    private String description;

    @Column(unique = true)
    private String barcode;

    @Column(unique = true)
    private String qrCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

}