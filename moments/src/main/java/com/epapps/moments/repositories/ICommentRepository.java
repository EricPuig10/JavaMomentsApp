package com.epapps.moments.repositories;

import com.epapps.moments.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
}
