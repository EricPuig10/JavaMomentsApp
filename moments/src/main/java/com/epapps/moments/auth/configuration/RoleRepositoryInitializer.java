package com.epapps.moments.auth.configuration;

import com.epapps.moments.models2.Role;
import com.epapps.moments.models2.User;
import com.epapps.moments.repositories2.AuthRepository;
import com.epapps.moments.repositories2.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

;

@Component
public class RoleRepositoryInitializer {

    private RoleRepository roleRepository;
    private AuthRepository authRepository;
    private PasswordEncoder encoder;

    public RoleRepositoryInitializer(RoleRepository roleRepository, AuthRepository authRepository, PasswordEncoder encoder) {
        this.roleRepository = roleRepository;
        this.authRepository = authRepository;
        this.encoder = encoder;
    }

    @PostConstruct
    public void addAvailableRoles(){
        if (!roleRepository.findAll().isEmpty()) {
            return;
        }

        List<Role> roles = List.of(
                new Role(1, Role.RoleName.ROLE_ADMIN),

                new Role(2, Role.RoleName.ROLE_USER)
        );

        roleRepository.saveAll(roles);

        if (!authRepository.findAll().isEmpty()) {
            return;
        }

        var user = new User();
        user.setRoles(roleRepository.findAll().stream().collect(Collectors.toSet()));
        user.setEmail("admin@admin.com");
        user.setUsername("Admin");
        user.setImg("https://pixabay.com/static/img/profile_image_dummy.svg");
        user.setPassword(encoder.encode("12345678"));

        authRepository.save(user);
    }
}
