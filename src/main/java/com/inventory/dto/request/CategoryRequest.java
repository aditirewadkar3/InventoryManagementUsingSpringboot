package com.inventory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {

    @NotBlank(message = "Category name is required")
    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String description;
}