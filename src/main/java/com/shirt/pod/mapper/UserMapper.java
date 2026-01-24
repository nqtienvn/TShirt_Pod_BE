package com.shirt.pod.mapper;

import com.shirt.pod.model.dto.request.CreateUserRequest;
import com.shirt.pod.model.dto.response.UserDTO;
import com.shirt.pod.model.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(CreateUserRequest request);

    List<UserDTO> toDTOList(List<User> users);
}
