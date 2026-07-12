package com.inventory.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferStockRequest {

    @NotNull
    private Long productId;

    @NotNull
    private Long sourceWarehouseId;

    @NotNull
    private Long destinationWarehouseId;

    @NotNull
    @Min(1)
    private Integer quantity;
}