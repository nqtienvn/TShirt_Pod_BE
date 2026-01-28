package com.shirt.pod.repository;

import com.shirt.pod.model.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    /**
     * Tìm tất cả variants của một sản phẩm
     */
    List<ProductVariant> findByBaseProductId(Long baseProductId);

    /**
     * Tìm tất cả variants đang active của một sản phẩm
     */
    List<ProductVariant> findByBaseProductIdAndActiveTrue(Long baseProductId);

    /**
     * Tìm variant theo SKU
     */
    Optional<ProductVariant> findBySku(String sku);

    /**
     * Kiểm tra SKU đã tồn tại chưa
     */
    boolean existsBySku(String sku);

    /**
     * Kiểm tra variant có tồn tại và đang active không
     */
    boolean existsByIdAndActiveTrue(Long id);
}
