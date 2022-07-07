package com.epapps.moments.services;

import com.epapps.moments.dtos.MomentRequestDto;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;
import com.epapps.moments.repositories.IMomentsRepository;
import org.springframework.stereotype.Service;

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


    public Moment updateAMoment(Moment momentToEdit, Long id){
    Moment moment = this.momentsRepository.findById(id).get();

        moment.setTitle(momentToEdit.getTitle());;
        moment.setDescription(momentToEdit.getDescription());
        moment.setImgUrl(momentToEdit.getImgUrl());
        moment.setUbication(momentToEdit.getUbication());
        moment.setLiked(!moment.isLiked());
    final Moment updatedMoment = this.momentsRepository.save(moment);
        return updatedMoment;
}

    @Override
    public Boolean deleteMoment(Long id) {
        Moment moment = this.momentsRepository.findById(id).get();
        this.momentsRepository.delete(moment);
        return true;
    }

    @Override
    public List<Moment> search(String search) {
        var searchCollection = this.momentsRepository.findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(search, search);
        return searchCollection;
    }


}
