package com.epapps.moments.dtos.user;

import lombok.Data;

@Data
public class UserReqDto {
    private String username;
    private String img;
    private String description;
    private String dateOfBirth;
    private String ubication;
}
