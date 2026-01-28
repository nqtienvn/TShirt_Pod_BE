package com.shirt.pod.repository;

import com.shirt.pod.model.entity.BaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaseProductRepository extends JpaRepository<BaseProduct, Long> {

    /**
     * Tìm tất cả sản phẩm đang active
     */
    List<BaseProduct> findByActiveTrue();

    /**
     * Tìm sản phẩm theo ID và đang active
     */
    Optional<BaseProduct> findByIdAndActiveTrue(Long id);

    /**
     * Kiểm tra tên sản phẩm đã tồn tại chưa
     */
    boolean existsByName(String name);

    /**
     * Kiểm tra tên sản phẩm đã tồn tại chưa (trừ sản phẩm hiện tại)
     */
    boolean existsByNameAndIdNot(String name, Long id);
}
