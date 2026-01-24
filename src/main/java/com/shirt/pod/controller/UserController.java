package com.shirt.pod.controller;

import com.shirt.pod.model.dto.request.CreateUserRequest;
import com.shirt.pod.model.dto.response.ApiResponse;
import com.shirt.pod.model.dto.response.UserDTO;
import com.shirt.pod.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ApiResponse<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();

        return ApiResponse.<List<UserDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Get all users successfully")
                .data(users)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);

        return ApiResponse.<UserDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Get user successfully")
                .data(user)
                .build();
    }

    @GetMapping("/email/{email}")
    public ApiResponse<UserDTO> getUserByEmail(@PathVariable String email) {
        UserDTO user = userService.getUserByEmail(email);

        return ApiResponse.<UserDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Get user successfully")
                .data(user)
                .build();
    }
    @PostMapping
    public ApiResponse<UserDTO> createUser(@RequestBody CreateUserRequest request) {
        UserDTO user = userService.createUser(request);

        return ApiResponse.<UserDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("User created successfully")
                .data(user)
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("User deleted successfully")
                .build();
    }
}
