package com.example.news.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
public class ResourceNotFoundExceptions extends RuntimeException {
    private String resourceName;  //lavozim
    private String resourceField; //name
    private Object object;      //nima qidirganligi


}