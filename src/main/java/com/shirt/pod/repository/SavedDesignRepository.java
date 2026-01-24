package com.shirt.pod.repository;

import com.shirt.pod.model.entity.SavedDesign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SavedDesignRepository extends JpaRepository<SavedDesign, Long> {
}