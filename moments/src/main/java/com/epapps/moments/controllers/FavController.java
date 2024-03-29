package com.epapps.moments.controllers;


import com.epapps.moments.dtos.fav.FavCommentReqDto;
import com.epapps.moments.dtos.fav.FavReqDto;
import com.epapps.moments.dtos.fav.FavResDto;
import com.epapps.moments.models.Fav;
import com.epapps.moments.models2.User;
import com.epapps.moments.services.IFavService;
import com.epapps.moments.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class FavController {
    private IFavService favService;
    private IUserService userService;

    public FavController(IFavService favService, IUserService userService) {
        this.favService = favService;
        this.userService = userService;
    }

    private User getAuth(Long id){
        return userService.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/favs")
    List<FavResDto> getAll(){
        return favService.getAll();

    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/moments/{id}/favs")
    List<FavResDto> getMomentFavs(@PathVariable Long id){
        return favService.getMomentFavs(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/favs")
    ResponseEntity<Boolean> like(@RequestBody FavReqDto fav){
        var isFaved = favService.toggleFav(fav);
        return new ResponseEntity<>(isFaved, HttpStatus.OK);
    }

    /*
    @PostMapping("/favscomment")
    ResponseEntity<Boolean> like(@RequestBody FavCommentReqDto fav){
        User auth = this.getAuth(1L);
        var isFaved = favService.toggleFavComment(fav, auth);
        return new ResponseEntity<>(isFaved, HttpStatus.OK);
    }*/





}
