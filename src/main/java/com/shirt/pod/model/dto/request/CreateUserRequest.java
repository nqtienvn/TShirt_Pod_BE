package com.shirt.pod.model.dto.request;

import com.shirt.pod.model.entity.enums.UserStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CreateUserRequest implements Serializable {

    String email;
    String password;
    String fullName;
    String phoneNumber;
    String avatarUrl;
    UserStatus status;
}
