package com.example.news.controller;

import com.example.news.entity.Post;
import com.example.news.entity.User;
import com.example.news.payload.ApiResponse;
import com.example.news.security.CurrentUser;
import com.example.news.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    final PostService postService;

    @PreAuthorize(value = "hasAuthority('ADD_POST')")
    @PostMapping
    public ResponseEntity addPost(@RequestBody Post post) {
        ApiResponse apiResponse = postService.addPost(post);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @PatchMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id,@RequestBody Post post){
        ApiResponse apiResponse=postService.edit(id,post);
        return ResponseEntity.status(apiResponse.isSuccess()? 200: 204).body(apiResponse);
    }
    @PreAuthorize(value = "hasAuthority('VIEW_POST')")
    @GetMapping
    public ResponseEntity getAll(){
        ApiResponse apiResponse=postService.geyAll();
        return ResponseEntity.status(apiResponse.isSuccess()? 200: 204).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('DELETE_POST')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        ApiResponse apiResponse=postService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 200: 204).body(apiResponse);

    }
}
