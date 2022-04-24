package com.example.news.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterDto {

    @NotNull(message = "fullName bosh bolmasligi kk")
    private String fullName;

    @NotNull(message = "username bosh bolmasligi kk")
    private String username;

    @NotNull(message = "password bosh bolmasligi kk")
    private String password;

    @NotNull(message = "pre password bosh bolmasligi kk")
    private String prePassword;

}
