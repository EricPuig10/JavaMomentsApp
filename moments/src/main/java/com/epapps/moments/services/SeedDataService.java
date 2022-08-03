package com.epapps.moments.services;

import com.epapps.moments.models.Moment;
import com.epapps.moments.models2.Role;
import com.epapps.moments.models2.User;
import com.epapps.moments.repositories.IMomentsRepository;
import com.epapps.moments.repositories.IUserRepository;
import com.epapps.moments.repositories2.AuthRepository;
import com.epapps.moments.repositories2.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class SeedDataService {

    private IMomentsRepository momentRepository;
    private AuthRepository authRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    public SeedDataService(IMomentsRepository momentRepository, AuthRepository authRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.momentRepository = momentRepository;
        this.authRepository = authRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @PostConstruct
    public void addData(){
        createUser();
        createMoment();
        createMoment();
        createMoment();
        createMoment();
        createMoment();
        createMoment();
        createMoment();
        createMoment();

    }



    public void createUser(){
        Set<Role> userRoles = Set.of(roleRepository.findByName(Role.RoleName.ROLE_USER).get());
        var eric = new User();
        eric.setUsername("eric_puig");
        eric.setRoles(userRoles);
        eric.setDescription("Hey");
        eric.setUbication("Tona");
        eric.setEmail("puigvendrelloetona@gmail.com");
        eric.setDateOfBirth("21/09/2000");
        eric.setFollowers(999);
        eric.setFollowing(1);
        eric.setImg("https://media-exp2.licdn.com/dms/image/C4D03AQG17WUfd78sgA/profile-displayphoto-shrink_400_400/0/1587477510501?e=1661385600&v=beta&t=lxPzlDjTbmYs0vSz-B7ef95gRfs-T5Dxj_RTLrcSO8Y");
        eric.setPassword(encoder.encode("12345678"));

        authRepository.save(eric);

    }

    public void createMoment(){
        //if(!momentRepository.findAll().isEmpty()) return;
        var eric = authRepository.findById(2L).get();
        var moment = new Moment();
        moment.setTitle("Title");
        moment.setUbication("Ubi");
        moment.setDescription("Des");
        moment.setImgUrl("https://cdn.pixabay.com/photo/2015/06/19/21/24/avenue-815297__340.jpg");
        moment.setCreator(eric);
        momentRepository.save(moment);
    }
}
