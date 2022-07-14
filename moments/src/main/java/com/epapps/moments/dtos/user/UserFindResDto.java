package com.epapps.moments.dtos.user;

import lombok.Data;

@Data
public class UserFindResDto {
    private Long id;
    private String name;
    private String userName;
    private String userImg;
    private long followers;
    private long following;
    private String description;
    private String dateOfBirth;
    private String ubication;
}
