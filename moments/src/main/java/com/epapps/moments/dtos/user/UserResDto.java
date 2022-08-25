package com.epapps.moments.dtos.user;

import lombok.Data;

@Data
public class UserResDto {

    private Long id;
    private String username;
    private String img;
    private String email;
    private long followers;
    private long following;
    private String description;
    private String dateOfBirth;
    private String ubication;


}
