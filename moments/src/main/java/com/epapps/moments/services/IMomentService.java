package com.epapps.moments.services;

import com.epapps.moments.dtos.MomentRequestDto;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;

import java.util.List;
import java.util.Map;

public interface IMomentService {

    List<Moment> getAll();

    Moment create(MomentRequestDto momentDto, User authUser);

    Moment findById(Long id);

    Moment updateAMoment(Moment momentToEdit, User authUser);

    Boolean deleteMoment(Long id, User auth);

    List<Moment> search(String search);

    Moment like(Long id, Moment moment);

    List<Moment> findByUserMoments(Long id);
}
