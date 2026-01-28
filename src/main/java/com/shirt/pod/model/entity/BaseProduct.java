package com.shirt.pod.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "base_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseProduct extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;

    private String material;

    private Boolean active;

}