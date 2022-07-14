package com.epapps.moments.dtos.comment;


import com.epapps.moments.models.Moment;
import lombok.Data;

@Data
public class CommentRequestDto {
    private String comment;
    private Long momentId;

}
