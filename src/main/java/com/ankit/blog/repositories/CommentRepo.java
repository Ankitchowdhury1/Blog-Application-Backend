package com.ankit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankit.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
