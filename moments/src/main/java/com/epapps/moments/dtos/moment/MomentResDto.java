package com.epapps.moments.dtos.moment;

import com.epapps.moments.models.User;
import lombok.Data;

@Data
public class MomentResDto {
    private String imgUrl;
    private String description;
    private String location;
    private boolean isLiked;
    private User creator;
    private int commentsCount;
    private Long id;
}
