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

    Moment updateAMoment(Moment momentToEdit, Long id);

    Boolean deleteMoment(Long id);

    List<Moment> search(String search);
}
