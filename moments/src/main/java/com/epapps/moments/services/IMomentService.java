package com.epapps.moments.services;

import com.epapps.moments.dtos.moment.MomentRequestDto;
import com.epapps.moments.dtos.moment.MomentResDto;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;

import java.util.List;

public interface IMomentService {

    List<Moment> getAll();

    Moment create(MomentRequestDto momentDto, User authUser);

    Moment findById(Long id);

    MomentResDto updateAMoment(MomentRequestDto momentRequestDto, Long id, User authUser);

    Boolean deleteMoment(Long id, User authUser);

    List<Moment> search(String search);

    //Moment like(Long id, Moment moment);

    List<Moment> findByUserMoments(Long id);
}
