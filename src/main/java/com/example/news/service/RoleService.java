package com.example.news.service;

import com.example.news.entity.Role;
import com.example.news.entity.User;
import com.example.news.payload.ApiResponse;
import com.example.news.payload.RoleDto;
import com.example.news.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository repository;
    public ApiResponse addRole(RoleDto roleDto) {
        if (repository.existsByName(roleDto.getName()))
            return new ApiResponse("Bunday role mavud",false);
        Role role = new Role(roleDto.getName(),roleDto.getPermissions(),roleDto.getDescription());

        repository.save(role);
        return new ApiResponse("Saved role",true);
    }

    public ApiResponse editRole(Long id, RoleDto roleDto) {
        Optional<Role> roleOptional = repository.findById(id);
        if (!roleOptional.isPresent()){
            return ApiResponse.builder().massage("Bunday role mavjud emas").success(false).build();
        }
        Role role = roleOptional.get();
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        role.setLavozimlarList(roleDto.getPermissions());
        Role save = repository.save(role);
        return ApiResponse.builder().success(true).massage("edited").object(save).build();
    }
}
