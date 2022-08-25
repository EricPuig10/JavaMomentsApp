package com.epapps.moments.controllers;

import com.epapps.moments.auth.facade.IAuthenticationFacade;
import com.epapps.moments.dtos.moment.MomentRequestDto;
import com.epapps.moments.dtos.moment.MomentResDto;
import com.epapps.moments.dtos.user.UserFindRequestDto;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models2.User;
import com.epapps.moments.services.IMomentService;
import com.epapps.moments.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin (origins="*")
public class MomentController {

    private IMomentService momentService;
    private IUserService userService;

    private IAuthenticationFacade authenticationFacade;

    public MomentController(IMomentService momentService, IUserService userService, IAuthenticationFacade authenticationFacade) {
        this.momentService = momentService;
        this.userService = userService;
        this.authenticationFacade = authenticationFacade;
    }

    /*
    private User getAuthUser(Long id) {
        return userService.getById(1L);
    }*/


    @GetMapping("/moments")
    List<MomentResDto> getAll(){
        User auth = authenticationFacade.getAuthUser();
        return momentService.getAll(auth);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping ("/moments/{id}")
    MomentResDto getMomentById(@PathVariable Long id){
        User auth = authenticationFacade.getAuthUser();
        var moment = this.momentService.findById(id, auth);
        return moment;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/moments")
    Moment createMoment(@RequestBody MomentRequestDto momentRequestDto){
        var authUser = authenticationFacade.getAuthUser();
        return momentService.create(momentRequestDto, authUser);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/moments/{id}")
    MomentResDto updateMoment(@PathVariable Long id, @RequestBody MomentRequestDto momentRequestDto) {
        User authUser = authenticationFacade.getAuthUser();

        return momentService.updateAMoment(momentRequestDto, id, authUser);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/moments/{id}")
    ResponseEntity<MomentResDto> deleteMoment(@PathVariable Long id) {
        User authUser = authenticationFacade.getAuthUser();
        var moment=  momentService.deleteMoment(id, authUser);
        return new ResponseEntity<>(moment, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value="/moments", params="search")

    List<MomentResDto> getSearch(@RequestParam String search){
        User authUser = authenticationFacade.getAuthUser();
        return momentService.search(search, authUser);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/moments/{id}/users")
    List<MomentResDto> getMomentsByUser(@PathVariable Long id){
        User authUser = authenticationFacade.getAuthUser();
        return momentService.findByUserMoments(id, authUser);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/fav-moments")
    List <MomentResDto> getUserFavMoments(){
        User auth = authenticationFacade.getAuthUser();
        return momentService.getUserFavedMoments(auth);
    }















}
