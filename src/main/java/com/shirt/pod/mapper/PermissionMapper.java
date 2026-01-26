package com.shirt.pod.mapper;

import com.shirt.pod.model.dto.response.PermissionDTO;
import com.shirt.pod.model.entity.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

    public PermissionDTO toDTO(Permission permission) {
        if (permission == null) {
            return null;
        }

        return PermissionDTO.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .createdDate(permission.getCreatedDate())
                .build();
    }
}
