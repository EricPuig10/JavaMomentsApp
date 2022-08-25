package com.epapps.moments.services;

import com.epapps.moments.dtos.moment.MomentRequestDto;
import com.epapps.moments.dtos.moment.MomentResDto;
import com.epapps.moments.exceptions.NotFoundException;
import com.epapps.moments.mappers.MomentMapper;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models2.User;
import com.epapps.moments.repositories.IMomentsRepository;
import com.factoria.moments.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MomentService implements IMomentService {

    private IMomentsRepository momentsRepository;


    public MomentService(IMomentsRepository momentsRepository) {
        this.momentsRepository = momentsRepository;
    }


    @Override
    public List<MomentResDto> getAll(User auth) {
        List<Moment> moments = momentsRepository.findAll();
        List <MomentResDto> resMoments = new ArrayList<>();
        moments.forEach(Moment -> {
            MomentResDto resMoment = new MomentMapper().mapToRes(Moment, auth);
            resMoments.add(resMoment);
        });
        return resMoments;

    }

    @Override
    public Moment create(MomentRequestDto momentDto, User auth) {
        Moment moment = new Moment();
        moment.setTitle(momentDto.getTitle());
        moment.setImgUrl(momentDto.getImgUrl());
        moment.setDescription(momentDto.getDescription());
        moment.setUbication(momentDto.getUbication());
        moment.setCreator(auth);
        return momentsRepository.save(moment);
    }

    @Override
    public MomentResDto findById(Long id, User auth) {
        Optional<Moment> foundMoment = momentsRepository.findById(id);
        if(foundMoment.isEmpty()) throw new NotFoundException("Moment Not Found", "M-404");
        MomentResDto resMoment = new MomentMapper().mapToRes(foundMoment.get(), auth);
        return resMoment;
    }


    public MomentResDto updateAMoment(MomentRequestDto momentRequestDto, Long id, User auth){

    var moment = momentsRepository.findById(id);

    if(moment.isEmpty()) throw new NotFoundException("Moment Not Found", "M-404");

    if(!moment.get().getCreator().getId().equals(auth.getId())) throw new BadRequestException("Moment can't be edited if you arent creator", "P-153");
        Moment updatedMoment = new MomentMapper().mapRequestToMomentToEdit(momentRequestDto, moment.get());
        momentsRepository.save(updatedMoment);
        MomentResDto momentRes = new MomentMapper().mapToRes(updatedMoment, auth);
        return momentRes;
    }

    @Override
    public MomentResDto deleteMoment(Long id, User auth){
        Moment moment = this.momentsRepository.findById(id).get();
        if(!moment.getCreator().getId().equals(auth.getId())) throw new BadRequestException("Not user auth", "P-154");
        MomentResDto resMoment = new MomentMapper().mapToRes(moment, auth);
        this.momentsRepository.delete(moment);
        return resMoment;
    }

    @Override
    public List<MomentResDto> search(String search, User auth) {
        return  new MomentMapper().mapMultipleMomentsToRes(momentsRepository.findByDescriptionOrTitleContaining(search), auth);
    }


    @Override
    public List<MomentResDto> findByUserMoments(Long id, User auth) {

        return new MomentMapper().mapMultipleMomentsToRes(momentsRepository.getMomentsByUserId(id), auth);
    }


    @Override
    public List<MomentResDto> getUserFavedMoments(User auth) {
        List<Moment> favMoments = momentsRepository.findFavs(auth.getId());
        List<MomentResDto> favMomentsRes = new ArrayList<>();
        favMoments.forEach(Moment -> {
            favMomentsRes.add(new MomentMapper().mapToRes(Moment, auth));
        });
        return favMomentsRes;
    }


}
