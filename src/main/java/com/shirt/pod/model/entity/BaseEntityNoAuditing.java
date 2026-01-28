package com.shirt.pod.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Base entity KHÔNG có auditing fields
 * Dùng cho các entity không cần track created/updated (ProductVariant, PrintArea)
 * vì database schema không có các columns này
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntityNoAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
