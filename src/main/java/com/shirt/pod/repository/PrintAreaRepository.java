package com.shirt.pod.repository;

import com.shirt.pod.model.entity.PrintArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrintAreaRepository extends JpaRepository<PrintArea, Long> {

    /**
     * Tìm tất cả print areas của một sản phẩm
     */
    List<PrintArea> findByBaseProductId(Long baseProductId);
}
