package com.inventory.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryCreateRequest {

    @NotNull
    private Long productId;

    @NotNull
    private Long warehouseId;

    @NotNull
    @Min(value = 0)
    private Integer quantity;
}