package com.shirt.pod.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "print_areas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrintArea extends BaseEntityCreatedOnly {
    private String name;
    @Column(name = "width_mm")
    private BigDecimal widthMm;
    @Column(name = "height_mm")
    private BigDecimal heightMm;
    @Column(name = "top_offset_mm")
    private BigDecimal topOffsetMm;
    @Column(name = "left_offset_mm")
    private BigDecimal leftOffsetMm;
    @Column(name = "mask_image_url")
    private String maskImageUrl;
}