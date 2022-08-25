package com.epapps.moments.services;

import com.epapps.moments.dtos.moment.MomentRequestDto;
import com.epapps.moments.dtos.moment.MomentResDto;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models2.User;

import java.io.IOException;
import java.util.List;

public interface IMomentService {

    List<MomentResDto> getAll(User auth);

    Moment create(MomentRequestDto momentDto, User authUser);

    MomentResDto findById(Long id, User auth);

    MomentResDto updateAMoment(MomentRequestDto momentRequestDto, Long id, User authUser);

    MomentResDto deleteMoment(Long id, User authUser);

    List<MomentResDto> search(String search, User auth);

    //Moment like(Long id, Moment moment);

    List<MomentResDto> findByUserMoments(Long id, User auth);

    List<MomentResDto> getUserFavedMoments(User auth);
}

