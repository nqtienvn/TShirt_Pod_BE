package com.shirt.pod.controller;

import com.shirt.pod.model.dto.request.CreateRoleRequest;
import com.shirt.pod.model.dto.request.UpdateRoleRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.shirt.pod.model.dto.response.RoleDTO;
import com.shirt.pod.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.shirt.pod.security.SecurityConstants.*;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@Tag(name = "Role", description = "APIs for managing roles and permissions")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @Operation(summary = "Get all roles", description = "Returns a list of all roles in the system. Requires ROLE_VIEW authority")
    @PreAuthorize("hasAuthority('" + ROLE_VIEW + "')")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get role by ID", description = "Returns details of a specific role by ID. Requires ROLE_VIEW authority")
    @PreAuthorize("hasAuthority('" + ROLE_VIEW + "')")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        RoleDTO role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @PostMapping
    @Operation(summary = "Create new role", description = "Create a new role with name and list of permissions. Requires ROLE_CREATE authority")
    @PreAuthorize("hasAuthority('" + ROLE_CREATE + "')")
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody CreateRoleRequest request) {
        RoleDTO role = roleService.createRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update role", description = "Update role information including name and list of permissions. Requires ROLE_UPDATE authority")
    @PreAuthorize("hasAuthority('" + ROLE_UPDATE + "')")
    public ResponseEntity<RoleDTO> updateRole(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRoleRequest request) {
        RoleDTO role = roleService.updateRole(id, request);
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete role", description = "Delete a role from the system by ID. Requires ROLE_DELETE authority")
    @PreAuthorize("hasAuthority('" + ROLE_DELETE + "')")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
