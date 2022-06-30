package com.epapps.moments.dtos;


import lombok.Data;

@Data
public class CommentRequestDto {
    private String comment;
    private Long momentId;
}
