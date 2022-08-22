package com.epapps.moments.services;

import com.epapps.moments.dtos.moment.MomentJsonRequest;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models2.Role;
import com.epapps.moments.models2.User;
import com.epapps.moments.repositories.IMomentsRepository;
import com.epapps.moments.repositories2.AuthRepository;
import com.epapps.moments.repositories2.RoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
        this.createUser();
        this.createUser2();
        this.createMultipleMoments();
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

    public void createUser2(){
        Set<Role> userRoles = Set.of(roleRepository.findByName(Role.RoleName.ROLE_USER).get());
        var ari = new User();
        ari.setUsername("aaaaari");
        ari.setRoles(userRoles);
        ari.setDescription("Hey");
        ari.setUbication("Tona");
        ari.setEmail("ari@gmail.com");
        ari.setDateOfBirth("08/01/2003");
        ari.setFollowers(1);
        ari.setFollowing(1);
        ari.setImg("https://thumbs.dreamstime.com/b/avatar-de-mujer-rubia-%C3%ADcono-perfil-chica-sonriente-para-medios-sociales-o-red-negocios-dise%C3%B1o-iconos-usuario-caracteres-198154292.jpg");
        ari.setPassword(encoder.encode("12345678"));

        authRepository.save(ari);

    }

    public Moment createMoment(String ubi, String title, String desc, String img, String username){
        var moment = new Moment();
        moment.setUbication(ubi);
        moment.setTitle(title);
        moment.setDescription(desc);
        moment.setImgUrl(img);
        moment.setCreator(authRepository.findByUsername(username).get());
        return moment;
    }

    public void createMultipleMoments(){
        List<Moment> moments = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<MomentJsonRequest>> typeReference = new TypeReference<List<MomentJsonRequest>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/moments.json");
        try{
            List<MomentJsonRequest> momentsReq = mapper.readValue(inputStream, typeReference);
            momentsReq.forEach(req -> moments.add(this.createMoment(req.getUbication(), req.getTitle(), req.getDescription(), req.getImgUrl(), req.getUsername())));
            momentRepository.saveAll(moments);
            System.out.println("Moments saved!");
        }catch (IOException e){
            System.out.println("Unable to save moments: "+ e.getMessage());
        }
    }


}
