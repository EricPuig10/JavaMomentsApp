package com.epapps.moments.dtos.comment;


import com.epapps.moments.dtos.user.UserResDtoMoment;
import com.epapps.moments.models2.User;
import lombok.Data;

@Data
public class CommentResDto {
    private Long id;
    private String comment;
    private boolean isFaved;
    private Long momentId;
    private UserResDtoMoment creator;
}
