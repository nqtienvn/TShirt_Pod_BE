package com.shirt.pod.service;

import com.shirt.pod.model.dto.response.PermissionDTO;

import java.util.List;

public interface PermissionService {
    /**
     * Lấy tất cả permissions
     */
    List<PermissionDTO> getAllPermissions();

    /**
     * Lấy permission theo ID
     */
    PermissionDTO getPermissionById(Long id);
}
