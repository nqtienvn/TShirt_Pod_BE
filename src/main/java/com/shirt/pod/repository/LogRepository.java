package com.shirt.pod.repository;

import com.shirt.pod.model.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
