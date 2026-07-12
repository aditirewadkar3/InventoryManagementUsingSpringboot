package com.inventory.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockInRequest {

    @NotNull
    private Long productId;

    @NotNull
    private Long warehouseId;

    @NotNull
    @Min(1)
    private Integer quantity;

}