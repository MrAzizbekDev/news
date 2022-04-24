package com.example.news.controller;

import com.example.news.entity.Post;
import com.example.news.payload.ApiResponse;
import com.example.news.payload.CommentDto;
import com.example.news.service.CommentService;
import com.example.news.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    final CommentService commentService;

    @PreAuthorize(value = "hasAuthority('ADD_COMMENT')")
    @PostMapping
    public ResponseEntity addPost(@RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = commentService.addPost(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_COMMENT')")
    @PatchMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = commentService.edit(id, commentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_COMMENT')")
    @GetMapping
    public ResponseEntity getAll() {
        ApiResponse apiResponse = commentService.geyAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('DELETE_COMMENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        ApiResponse apiResponse = commentService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
    }
    @PreAuthorize("hasAuthority('DELETE_MY_COMMENT')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMy(@PathVariable Long id){
        ApiResponse apiResponse=commentService.deleteMy(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse);
    }


}
