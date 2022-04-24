package com.example.news.repository;

import com.example.news.entity.Comment;
import com.example.news.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
     


}
