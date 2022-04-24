package com.example.news.controller;

import com.example.news.entity.User;
import com.example.news.payload.ApiResponse;
import com.example.news.payload.RoleDto;
import com.example.news.payload.UserDto;
import com.example.news.security.CurrentUser;
import com.example.news.service.RoleService;
import com.example.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    final RoleService roleService;

    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping
    public HttpEntity<?> addRole( @Valid @RequestBody RoleDto roleDto){
      ApiResponse apiResponse=roleService.addRole(roleDto);
      return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_ROLE')")
    @PutMapping("/{id}")
    public HttpEntity<?> editRole(
            @PathVariable Long id, @Valid @RequestBody RoleDto roleDto){
      ApiResponse apiResponse=roleService.editRole(id,roleDto);
      return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }
}
