package com.epapps.moments.dtos.moment;

import com.epapps.moments.dtos.user.UserResDtoMoment;
import com.epapps.moments.models.User;
import lombok.Data;

@Data
public class MomentResDto {
    private String imgUrl;
    private String description;
    private String ubication;
    private String title;
    private boolean isFaved;
    private int favsCount;
    private UserResDtoMoment creator;
    private int commentsCount;
    private Long id;
}
