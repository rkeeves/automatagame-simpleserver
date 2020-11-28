package com.deiksoftdev.automatagame.dto;

import lombok.Data;

@Data
public class UserScoreUpdateDTO {

    String userName;

    String scenario;

    Integer score;
}
