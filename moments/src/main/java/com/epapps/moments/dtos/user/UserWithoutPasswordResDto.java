package com.epapps.moments.dtos.user;

import lombok.Data;

@Data
public class UserWithoutPasswordResDto {

    private Long id;
    private String name;
    private String userName;
    private String userImg;
    private String email;
    private long followers;
    private long following;
    private String description;
    private String dateOfBirth;
    private String ubication;


}
