package com.example.news.payload;

import com.example.news.entity.enums.PermissionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto {

    @NotBlank
    private String text;

   @NotEmpty
    private Long postId;
}
