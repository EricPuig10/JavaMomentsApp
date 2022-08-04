package com.epapps.moments.dtos.fav;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FavResDto {
    Long id;
    Long momentId;
    Long faverId;
}