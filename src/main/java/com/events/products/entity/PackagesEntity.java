package com.events.products.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PACKAGES")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PackagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

//    @OneToMany(mappedBy = "packages", cascade = CascadeType.ALL)
//    private Set<ProductEntity> products = new HashSet<>();
}
