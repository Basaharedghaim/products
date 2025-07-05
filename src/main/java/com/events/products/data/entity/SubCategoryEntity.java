package com.events.products.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SUB_CATEGORY")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;
}
