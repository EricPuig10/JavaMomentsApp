package com.epapps.moments.repositories;

import com.epapps.moments.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
