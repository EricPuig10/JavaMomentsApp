package com.epapps.moments.dtos.comment;


import com.epapps.moments.models.User;
import lombok.Data;

@Data
public class CommentResDto {
    private Long id;
    private String comment;
    private boolean isLiked;
    private Long momentId;
    private User creator;
}
