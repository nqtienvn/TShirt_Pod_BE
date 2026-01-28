package com.shirt.pod.model.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRoleRequest {
    @Size(max = 50, message = "Role name must not exceed 50 characters")
    private String name;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    /**
     * Danh sách permission IDs để gán cho role.
     * Nếu null, không cập nhật permissions.
     * Nếu empty set, xóa tất cả permissions.
     */
    private Set<Long> permissionIds;
}
