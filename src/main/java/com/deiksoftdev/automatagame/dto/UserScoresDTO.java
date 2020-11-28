package com.deiksoftdev.automatagame.dto;

import lombok.Data;

import java.util.Map;

@Data
public class UserScoresDTO {

    private String userName;

    private Map<String,Integer> scores;
}
