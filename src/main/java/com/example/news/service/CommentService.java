package com.example.news.service;

import com.example.news.entity.Comment;
import com.example.news.entity.Post;
import com.example.news.payload.ApiResponse;
import com.example.news.payload.CommentDto;
import com.example.news.repository.CommentRepository;
import com.example.news.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    final CommentRepository commentRepository;
    final PostRepository postRepository;
    public ApiResponse addPost(CommentDto commentDto) {
        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (optionalPost.isEmpty()) return  new ApiResponse("Not  post this id",false);
        Comment comment = new Comment();
        comment.setPost(optionalPost.get());
        comment.setText(commentDto.getText());
        commentRepository.save(comment);
        return new ApiResponse("saved",true);

    }

    public ApiResponse edit(Long id, CommentDto commentDto) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isEmpty()) return new ApiResponse("Not comment this id",false);
        Comment comment = optionalComment.get();
         comment.setText(comment.getText());
        Optional<Post> optional = postRepository.findById(id);
        if (optional.isEmpty()) return new ApiResponse("Not this post",false);
       comment.setPost(optional.get());
        Comment save = commentRepository.save(comment);
        return new ApiResponse("Edited",true,save);

    }

    public ApiResponse geyAll() {
        List<Comment> all = commentRepository.findAll();
        return new ApiResponse("Mana ",true,all);
    }

    public ApiResponse delete(Long id) {
        Optional<Comment> optional = commentRepository.findById(id);
        if (optional.isEmpty()) return new ApiResponse("Not this post",false);
        commentRepository.delete(optional.get());
        return new ApiResponse("Deleted",true);
    }

    public ApiResponse deleteMy(Long id) {
        Optional<Comment> optional = commentRepository.findById(id);
        if (optional.isEmpty()) return new ApiResponse("Not this post",false);
        commentRepository.delete(optional.get());
        return new ApiResponse("Deleted",true);

    }
}
