package com.epapps.moments.auth.facade;

import com.epapps.moments.models2.User;
import com.epapps.moments.repositories2.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {
    @Autowired
    AuthRepository authRepository;

    public User getAuthUser() {
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return authRepository.findByUsername(userName).get();
    }
}
