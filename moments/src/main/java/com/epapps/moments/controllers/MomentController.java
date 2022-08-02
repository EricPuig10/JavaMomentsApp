package com.epapps.moments.controllers;

import com.epapps.moments.dtos.moment.MomentRequestDto;
import com.epapps.moments.dtos.moment.MomentResDto;
import com.epapps.moments.dtos.user.UserFindRequestDto;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;
import com.epapps.moments.services.IMomentService;
import com.epapps.moments.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin (origins="*")
public class MomentController {

    private IMomentService momentService;
    private IUserService userService;

    public MomentController(IMomentService momentService, IUserService userService) {
        this.momentService = momentService;
        this.userService = userService;
    }

    private User getAuthUser(Long id) {
        return userService.getById(1L);
    }

    @GetMapping("/moments")
    List<MomentResDto> getAll(){
        User auth = this.getAuthUser(1L);
        return momentService.getAll(auth);
    }

    @GetMapping ("/moments/{id}")
    Moment getMomentById(@PathVariable Long id){
        var moment = this.momentService.findById(id);
        return moment;
    }

    @PostMapping("/moments")
    Moment createMoment(@RequestBody MomentRequestDto momentRequestDto){
        User authUser = getAuthUser(momentRequestDto.getUserId());
        return momentService.create(momentRequestDto, authUser);
    }


    @PutMapping("/moments/{id}")
    MomentResDto updateMoment(@PathVariable Long id, @RequestBody MomentRequestDto momentRequestDto){
        User authUser = getAuthUser(1L);

        return momentService.updateAMoment(momentRequestDto, id, authUser);
    }


    @DeleteMapping("/moments/{id}")
    ResponseEntity<MomentResDto> deleteMoment(@PathVariable Long id) {
        User authUser = getAuthUser(1L);
        var moment=  momentService.deleteMoment(id, authUser);
        return new ResponseEntity<>(moment, HttpStatus.OK);
    }

    /*
    @PatchMapping("/moments/{id}/like")
    Moment like(@PathVariable Long id, @RequestBody Moment moment){
        //User auth = getAuthUser();
        return momentService.like(id, moment);
    }*/



    @GetMapping(value="/moments", params="search")
    List<Moment> getSearch(@RequestParam String search){
        return momentService.search(search);
    }

    @GetMapping("/moments/{id}/users")
    List<Moment> getMomentsByUser(@PathVariable Long id){
        return momentService.findByUserMoments(id);
    }

    @GetMapping("/fav-moments")
    List <MomentResDto> getUserFavMoments(){
        User auth = this.getAuthUser(1L);
        return momentService.getUserFavedMoments(auth);
    }















}
