package com.epapps.moments.services;

import com.epapps.moments.dtos.moment.MomentRequestDto;
import com.epapps.moments.dtos.moment.MomentResDto;
import com.epapps.moments.exceptions.NotFoundException;
import com.epapps.moments.mappers.MomentMapper;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;
import com.epapps.moments.repositories.IMomentsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MomentService implements IMomentService {

    private IMomentsRepository momentsRepository;

    public MomentService(IMomentsRepository momentsRepository) {
        this.momentsRepository = momentsRepository;
    }

    @Override
    public List<Moment> getAll() {
        return momentsRepository.findAll();

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
    public Moment findById(Long id) {

        return momentsRepository.findById(id).get();
    }


    public MomentResDto updateAMoment(MomentRequestDto momentRequestDto, Long id, User auth){

    var moment = momentsRepository.findById(id);

    if(moment.isEmpty()) throw new NotFoundException("Moment Not Found", "M-404");

    if(auth.getId() != moment.get().getCreator().getId()) {
        return null;
    }
        Moment updatedMoment = new MomentMapper().mapRequestToMomentToEdit(momentRequestDto, moment.get());
        momentsRepository.save(updatedMoment);
        MomentResDto momentRes = new MomentMapper().mapToRes(updatedMoment, auth);
        return momentRes;
    }

    @Override
    public Boolean deleteMoment(Long id, User auth) {
        Moment moment = this.momentsRepository.findById(id).get();
        if(!moment.getCreator().getId().equals(auth.getId())) return null;
        this.momentsRepository.delete(moment);
        return true;
    }

    @Override
    public List<Moment> search(String search) {
        var searchCollection = this.momentsRepository.findByDescriptionOrTitleContaining(search);
        return searchCollection;
    }

    /*
    @Override
    public Moment like(Long id, Moment moment) {
        Moment momentToLike = momentsRepository.findById(moment.getId()).get();
        //if(moment.getCreator().getId() == auth.getId()) return null;
        moment.setLiked(!momentToLike.isLiked());
        if(momentToLike.isLiked() == true) {
            moment.setLikes(moment.getLikes()-1);
        } else {
            moment.setLikes(moment.getLikes()+1);
        }
        Moment momentLiked = momentsRepository.save(moment);
        return momentLiked;
    }
    */


    @Override
    public List<Moment> findByUserMoments(Long id) {


        List<Moment> userMoments = new ArrayList<>();

        momentsRepository.getMomentsByUserId(id).forEach(userMoments::add);

        return userMoments;
    }


}
