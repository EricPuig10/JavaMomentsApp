package com.epapps.moments.services;

import com.epapps.moments.dtos.fav.FavReqDto;
import com.epapps.moments.exceptions.NotFoundException;
import com.epapps.moments.mappers.FavMapper;
import com.epapps.moments.models.Fav;
import com.epapps.moments.models.User;
import com.epapps.moments.repositories.IFavRepository;
import com.epapps.moments.repositories.IMomentsRepository;
import com.epapps.moments.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavService implements IFavService{

    IFavRepository favRepository;
    IMomentsRepository momentsRepository;
    IUserRepository userRepository;

    public FavService(IFavRepository favRepository, IMomentsRepository momentsRepository, IUserRepository userRepository) {
        this.favRepository = favRepository;
        this.momentsRepository = momentsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Fav> getAll() {
        return favRepository.findAll();
    }

    @Override
    public List<Fav> getMomentFavs(Long id) {
        return favRepository.findByMomentId(id);
    }

    @Override
    public String toggleFav(FavReqDto req, User auth) {
        var moment = momentsRepository.findById(req.getMomentId());
        var faver = auth;
        if(moment.isEmpty() || faver == null) throw  new NotFoundException("Moment is Empty", "P-153");
        if(moment.get().getCreator() == faver) throw new NotFoundException("Moment creator can't like its own moment", "P-153");
        var fav = new FavMapper().mapReqToFav(faver, moment.get());
        var favs = favRepository.findAll();
        var result = this.checkIfLikeAlreadyExists(fav);
        if(result.isPresent()){
            return this.disfav(result.get());
        }
        return this.fav(fav);
    }

    private Optional<Fav> checkIfLikeAlreadyExists(Fav fav){
        List<Fav> favs = favRepository.findAll();
        return favs.stream().filter(Fav -> Fav.getFaver() == fav.getFaver()).findAny();
    }

    private String disfav(Fav fav){
        favRepository.delete(fav);
        return  "User "+fav.getFaver().getName()+" disliked moment with id: "+fav.getMoment().getId()+".";
    }

    private String fav(Fav fav){
        favRepository.save(fav);
        return "User "+fav.getFaver().getName()+" liked moment with id: "+fav.getMoment().getId()+".";
    }


}
