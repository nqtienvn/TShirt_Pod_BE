package com.shirt.pod.repository;

import com.shirt.pod.model.entity.BaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    /**
     * Tìm sản phẩm theo ID với variants (sử dụng fetch join)
     * Note: Không thể fetch cả variants và printAreas cùng lúc vì Hibernate không cho phép MultipleBagFetchException
     * PrintAreas sẽ được load riêng trong transaction
     */
    @Query("SELECT DISTINCT p FROM BaseProduct p " +
           "LEFT JOIN FETCH p.variants " +
           "WHERE p.id = :id")
    Optional<BaseProduct> findByIdWithVariants(@Param("id") Long id);
}
