package com.shirt.pod.mapper;

import com.shirt.pod.model.dto.response.RoleDTO;
import com.shirt.pod.model.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    private final PermissionMapper permissionMapper;

    public RoleDTO toDTO(Role role) {
        if (role == null) {
            return null;
        }

        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .permissions(role.getPermissions().stream()
                        .map(permissionMapper::toDTO)
                        .collect(Collectors.toSet()))
                .createdDate(role.getCreatedDate())
                .build();
    }
}
