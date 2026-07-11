package com.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "warehouses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100,unique = true)
    private String name;

    @Column(nullable = false,length = 150)
    private String location;

    @Column(length = 100)
    private String managerName;

    @Column(length = 15)
    private String contactNumber;

    @Column(length = 100)
    private String email;
}