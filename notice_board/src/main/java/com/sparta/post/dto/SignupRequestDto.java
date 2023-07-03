package com.sparta.post.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    //최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)
    @Pattern(regexp = "^[0-9a-z]{4,10}$")
    @NotBlank
    private String username;

    //최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)
    @Pattern(regexp = "^[0-9a-zA-Z]{8,15}$")
    @NotBlank
    private String password;

}