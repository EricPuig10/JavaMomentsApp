package com.epapps.moments.dtos;


import com.epapps.moments.models.Moment;
import lombok.Data;

@Data
public class CommentRequestDto {
    private String comment;
    private Long momentId;

}
