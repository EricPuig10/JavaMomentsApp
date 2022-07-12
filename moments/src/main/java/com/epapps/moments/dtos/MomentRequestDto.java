package com.epapps.moments.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MomentRequestDto {
    private String title;
    private String imgUrl;
    private String description;
    private String ubication;
}
