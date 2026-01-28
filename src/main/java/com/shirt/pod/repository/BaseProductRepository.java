package com.shirt.pod.repository;

import com.shirt.pod.model.entity.BaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseProductRepository extends JpaRepository<BaseProduct, Long> {
    List<BaseProduct> findByActiveTrue();
}
