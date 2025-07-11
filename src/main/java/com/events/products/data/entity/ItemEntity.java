package com.events.products.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ITEM")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private float price;

    @Column(name = "ITEM_IMAGE_PATH")
    private String itemImagePath;

    @Column(name = "STARS")
    private Integer stars;

    //TODO Later
    @Column(name = "ADD_ONS")
    private String addOns;

    @Column(name = "UPDATED_ON")
    private LocalDateTime updatedOn;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "CREATED_BY")
    private String createdBy;

    @ManyToMany
    @JoinTable(
            name = "ITEM_SUB_CATEGORY",
            joinColumns = @JoinColumn(name = "ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name = "SUB_CATEGORY_ID")
    )
    private Set<SubCategoryEntity> subCategoriesForItems = new HashSet<>();

}

