package com.epapps.moments.dtos.comment;


import com.epapps.moments.models.Moment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CommentRequestDto {
    private String comment;
    private Long momentId;
    private Long userId;

}
