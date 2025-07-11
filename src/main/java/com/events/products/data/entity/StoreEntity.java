package com.events.products.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "STORE")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "STORE_IMAGE_PATH")
    private String storeImagePath;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STARS")
    private double stars;

    @Column(name = "UPDATED_ON")
    private LocalDateTime updatedOn;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "CREATED_BY")
    private String createdBy;

    @ManyToMany(mappedBy = "stores")
    @JsonIgnore
    private Set<CategoryEntity> categories = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "STORE_SUB_CATEGORY",
            joinColumns = @JoinColumn(name = "STORE_ID"),
            inverseJoinColumns = @JoinColumn(name = "SUB_CATEGORY_ID")
    )
    private Set<SubCategoryEntity> subCategories = new HashSet<>();

}
