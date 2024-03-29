package com.epapps.moments.services;

import com.epapps.moments.auth.facade.IAuthenticationFacade;
import com.epapps.moments.dtos.fav.FavCommentReqDto;
import com.epapps.moments.dtos.fav.FavReqDto;
import com.epapps.moments.dtos.fav.FavResDto;
import com.epapps.moments.exceptions.NotFoundException;
import com.epapps.moments.mappers.FavMapper;
import com.epapps.moments.models.Fav;
import com.epapps.moments.models2.User;
import com.epapps.moments.repositories.ICommentRepository;
import com.epapps.moments.repositories.IFavRepository;
import com.epapps.moments.repositories.IMomentsRepository;
import com.epapps.moments.repositories.IUserRepository;
import com.factoria.moments.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavService implements IFavService{

    IFavRepository favRepository;
    IMomentsRepository momentsRepository;
    IAuthenticationFacade authenticationFacade;

    public FavService(IFavRepository favRepository, IMomentsRepository momentsRepository, IAuthenticationFacade authenticationFacade){
        this.favRepository = favRepository;
        this.momentsRepository = momentsRepository;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public List<FavResDto> getAll() {
        return new FavMapper().mapMultipleFavsToFavsDto(favRepository.findAll());
    }

    @Override
    public List<FavResDto> getMomentFavs(Long id) {
        return new FavMapper().mapMultipleFavsToFavsDto(favRepository.findByMomentId(id));
    }

    @Override
    public boolean toggleFav(FavReqDto req) {
        var moment = momentsRepository.findById(req.getMomentId());
        var faver = authenticationFacade.getAuthUser();
        if(faver == null) throw  new NotFoundException("User Not Found", "P-153");
        if(moment.get().getCreator() == faver) throw new BadRequestException("Moment creator can't like its own moment", "P-153");
        var fav = new FavMapper().mapReqToFav(faver, moment.get());
        var result = this.checkIfLikeAlreadyExists(fav);
        if(result.isPresent()){
            return this.disfav(result.get());
        }
        return this.fav(fav);
    }

    /*
    @Override
    public boolean toggleFavComment(FavCommentReqDto req, User auth) {
        var comment = commentRepository.findById(req.getCommentId());
        var faver = auth;
        if(comment.isEmpty() || faver == null) throw  new NotFoundException("Comment is Empty", "P-153");
        if(comment.get().getCreator() == faver) throw new BadRequestException("Comment creator can't like its own comment", "P-153");
        var fav = new FavMapper().mapCommentReqToFav(faver, comment.get());
        var result = this.checkIfCommentIsAlreadyLiked(fav);
        if(result.isPresent()){
            return this.disfav(result.get());
        }
        return this.fav(fav);
    }*/

    private Optional<Fav> checkIfLikeAlreadyExists(Fav fav){
        List<Fav> favs = favRepository.findByMomentId(fav.getMoment().getId());
        return favs.stream().filter(Fav -> Fav.getFaver() == fav.getFaver()).findAny();
    }

    /*
    private Optional<Fav> checkIfCommentIsAlreadyLiked(Fav fav){
        List<Fav> favs = favRepository.findByMomentId(fav.getComment().getId());
        return favs.stream().filter(Fav -> Fav.getFaver() == fav.getFaver()).findAny();
    } */

    private boolean disfav(Fav fav){
        favRepository.delete(fav);
        return false;
    }

    private boolean fav(Fav fav){
        favRepository.save(fav);
        return true;
    }


}
