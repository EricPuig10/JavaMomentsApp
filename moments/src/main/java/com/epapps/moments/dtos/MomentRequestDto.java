package com.epapps.moments.dtos;

import lombok.Data;

@Data
public class MomentRequestDto {
    private String title;
    private String imgUrl;
    private String description;
    private String ubication;
}
