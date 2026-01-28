package com.shirt.pod.service;

import com.shirt.pod.model.dto.request.CreateUserRequest;
import com.shirt.pod.model.dto.response.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO getUserByEmail(String email);

    UserDTO createUser(CreateUserRequest request);

    void deleteUser(Long id);
}
