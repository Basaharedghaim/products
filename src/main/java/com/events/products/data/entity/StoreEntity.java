package com.events.products.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    @ManyToMany(mappedBy = "stores")
    @JsonIgnore
    private Set<CategoryEntity> categories = new HashSet<>();

}
