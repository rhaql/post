package com.sparta.post.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String contents;

}
