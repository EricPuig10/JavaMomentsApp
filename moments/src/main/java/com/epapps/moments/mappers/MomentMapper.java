package com.epapps.moments.mappers;


import com.epapps.moments.dtos.MomentRequestDto;
import com.epapps.moments.models.Moment;

public class MomentMapper {
    public Moment mapToMoment(MomentRequestDto momentDto){
        Moment moment = new Moment();
        moment.setTitle(momentDto.getTitle());
        moment.setImgUrl(momentDto.getImgUrl());
        moment.setDescription(momentDto.getDescription());
        moment.setUbication(momentDto.getUbication());
        return moment;
    }
}
