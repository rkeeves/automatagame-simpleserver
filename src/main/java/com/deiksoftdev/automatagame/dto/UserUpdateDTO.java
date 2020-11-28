package com.deiksoftdev.automatagame.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDTO {

    private long id;

    @NotBlank(message="name cant be blank")
    private String name;

    @NotBlank(message="email cant be blank")
    private String email;

    private boolean admin;

    private boolean disabled;
}
