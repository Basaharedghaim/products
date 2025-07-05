package com.events.products.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

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
    private String image;
    private Integer stars;
    //TODO Later
    private String addOns;



    @ManyToMany
    @JoinTable(
            name = "PRODUCT_CATEGORY",
            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    private Set<CategoryEntity> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "PRODUCT_STORE",
            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "STORE_ID")
    )
    private Set<StoreEntity> stores = new HashSet<>();



}

