package com.deiksoftdev.automatagame.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterDTO {

    @NotBlank(message="name cant be blank")
    private String name;

    @NotBlank(message="password cant be blank")
    private String password;

    @NotBlank(message="email cant be blank")
    @Email(message="email must be a valid email address")
    private String email;

}
