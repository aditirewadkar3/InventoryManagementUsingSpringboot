package com.inventory.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull(message = "Selling price is required")
    @DecimalMin(value = "0.0")
    private BigDecimal sellingPrice;

    @NotNull(message = "Cost price is required")
    @DecimalMin(value = "0.0")
    private BigDecimal costPrice;

    private String description;

    @NotNull(message = "Category Id is required")
    private Long categoryId;

}