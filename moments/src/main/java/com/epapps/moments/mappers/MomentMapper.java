package com.epapps.moments.mappers;


import com.epapps.moments.dtos.moment.MomentRequestDto;
import com.epapps.moments.dtos.moment.MomentResDto;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;

public class MomentMapper {
    public Moment mapToMoment(MomentRequestDto momentDto){
        Moment moment = new Moment();
        moment.setTitle(momentDto.getTitle());
        moment.setImgUrl(momentDto.getImgUrl());
        moment.setDescription(momentDto.getDescription());
        moment.setUbication(momentDto.getUbication());
        return moment;
    }

    public MomentResDto mapToRes(Moment moment, User auth){
        MomentResDto resMoment = new MomentResDto();
        resMoment.setDescription(moment.getDescription());
        resMoment.setUbication(moment.getUbication());
        resMoment.setTitle(moment.getTitle());
        resMoment.setImgUrl(moment.getImgUrl());
        resMoment.setFaved(moment.isFaved(auth));
        resMoment.setFavsCount(moment.favsCount());
        resMoment.setCommentsCount(moment.commentsCount());
        resMoment.setId(moment.getId());
        resMoment.setCreator(new UserMapper().mapUserToResDtoMoment(moment.getCreator()));
        return  resMoment;
    }

    public Moment mapRequestToMomentToEdit(MomentRequestDto momentRequestDto, Moment moment){
        moment.setImgUrl(momentRequestDto.getImgUrl());
        moment.setDescription(momentRequestDto.getDescription());
        moment.setUbication(momentRequestDto.getUbication());
        return moment;
    }


}
