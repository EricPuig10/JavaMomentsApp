package com.epapps.moments.dtos.fav;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FavCommentReqDto {
    Long commentId; //moment.getcomments.getcomment
    Long faverId;
}
