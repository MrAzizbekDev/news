package com.example.news.service;

import com.example.news.entity.Post;
import com.example.news.payload.ApiResponse;
import com.example.news.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    final PostRepository postRepository;
    public ApiResponse addPost(Post post) {
        postRepository.save(post);
        return new ApiResponse("saved",true);

    }

    public ApiResponse edit(Long id, Post post) {
        Optional<Post> optional = postRepository.findById(id);
        if (optional.isEmpty()) return new ApiResponse("Not this post",false);
        Post post1 = optional.get();
        post1.setText(post.getText());
        post1.setTitle(post.getTitle());
        post1.setUrl(post.getUrl());
        postRepository.save(post1);
        return new ApiResponse("Edited",true,post1);

    }

    public ApiResponse geyAll() {
        List<Post> all = postRepository.findAll();
        return new ApiResponse("Mana ",true,all);
    }

    public ApiResponse delete(Long id) {
        Optional<Post> optional = postRepository.findById(id);
        if (optional.isEmpty()) return new ApiResponse("Not this post",false);
        postRepository.delete(optional.get());
        return new ApiResponse("Deleted",true);
    }

}
