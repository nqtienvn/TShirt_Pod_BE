package com.shirt.pod.service;

import com.shirt.pod.model.dto.request.CreateRoleRequest;
import com.shirt.pod.model.dto.request.UpdateRoleRequest;
import com.shirt.pod.model.dto.response.RoleDTO;

import java.util.List;

public interface RoleService {
    /**
     * Lấy tất cả roles
     */
    List<RoleDTO> getAllRoles();

    /**
     * Lấy role theo ID
     */
    RoleDTO getRoleById(Long id);

    /**
     * Tạo role mới
     */
    RoleDTO createRole(CreateRoleRequest request);

    /**
     * Cập nhật role (bao gồm gán permissions)
     */
    RoleDTO updateRole(Long id, UpdateRoleRequest request);

    /**
     * Xóa role (không cho xóa SUPER_ADMIN)
     */
    void deleteRole(Long id);
}
