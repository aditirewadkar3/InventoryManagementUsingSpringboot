package com.inventory.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponse {

    private long totalCategories;

    private long totalProducts;

    private long totalWarehouses;

    private long totalInventoryRecords;

    private long lowStockProducts;

}