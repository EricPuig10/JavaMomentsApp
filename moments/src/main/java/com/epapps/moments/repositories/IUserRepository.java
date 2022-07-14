package com.epapps.moments.repositories;

import com.epapps.moments.models.Comment;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {


}
