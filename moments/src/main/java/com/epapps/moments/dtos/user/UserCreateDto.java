package com.epapps.moments.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserCreateDto {
    private String username;
    //private String email;
    private String password;
}
