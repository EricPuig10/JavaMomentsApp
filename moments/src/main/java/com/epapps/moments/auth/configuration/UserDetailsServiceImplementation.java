package com.epapps.moments.auth.configuration;


import com.epapps.moments.models2.User;
import com.epapps.moments.repositories2.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    AuthRepository authRepository;

    @Autowired
    public UserDetailsServiceImplementation(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImplementation.build(user);
    }
}
