package com.inventory.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseResponse {

    private Long id;

    private String name;

    private String location;

    private String managerName;

    private String contactNumber;

    private String email;
}