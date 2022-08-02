package com.epapps.moments.dtos.moment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MomentToEditRequestDto {
    private String title;
    private String imgUrl;
    private String description;
    private String ubication;
    private Long userId;
}
