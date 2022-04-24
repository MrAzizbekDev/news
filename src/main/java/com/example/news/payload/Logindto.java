package com.example.news.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Logindto {
    @NotNull(message = "username bosh bolmasligi kk")
    private String username;

    @NotNull(message = "password bosh bolmasligi kk")
    private String password;

}
