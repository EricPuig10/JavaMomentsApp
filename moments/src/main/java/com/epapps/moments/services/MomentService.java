package com.epapps.moments.services;

import com.epapps.moments.dtos.moment.MomentRequestDto;
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


    public Moment updateAMoment(Moment momentToEdit, User auth){

    Moment moment = this.momentsRepository.findById(momentToEdit.getId()).get();

    if(auth.getId() != moment.getCreator().getId()) {
        return null;
    }
        moment.setTitle(momentToEdit.getTitle());;
        moment.setDescription(momentToEdit.getDescription());
        moment.setImgUrl(momentToEdit.getImgUrl());
        moment.setUbication(momentToEdit.getUbication());
        moment.setCreator(auth);
        Moment updatedMoment = this.momentsRepository.save(moment);
        return updatedMoment;
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
        var searchCollection = this.momentsRepository.findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(search, search);
        return searchCollection;
    }

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

    @Override
    public List<Moment> findByUserMoments(Long id) {


        List<Moment> userMoments = new ArrayList<>();

        momentsRepository.getMomentsByUserId(id).forEach(userMoments::add);

        return userMoments;
    }


}
