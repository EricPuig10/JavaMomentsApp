package com.epapps.moments.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserCreateDto {
    private String name;
    private String userName;
    private String email;
    private String password;
}
