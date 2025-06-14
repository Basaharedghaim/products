package com.events.products.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.function.Function;

@Entity
@Table(name = "CATEGORY")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity  {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
}
