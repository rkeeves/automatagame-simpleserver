package com.deiksoftdev.automatagame.service;

import com.deiksoftdev.automatagame.exception.UserByNameNotFoundException;
import com.deiksoftdev.automatagame.model.UserRepository;
import com.deiksoftdev.automatagame.dto.UserScoreUpdateDTO;
import com.deiksoftdev.automatagame.dto.UserScoresDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserScoreService {

    private final UserRepository userRepository;

    public UserScoresDTO createEmptyUserScoreUpdateDTO(){
        return new UserScoresDTO();
    }

    public UserScoresDTO getScores(String userName) throws UserByNameNotFoundException {
        final var user = userRepository.findByName(userName);
        if(user == null)
            throw new UserByNameNotFoundException();
        final var dto = new UserScoresDTO();
        dto.setUserName(userName);
        dto.setScores(user.getScores());
        return dto;
    }

    @Transactional
    public void update(String userName, UserScoreUpdateDTO dto) throws UserByNameNotFoundException {
        final var user = userRepository.findByName(userName);
        if(user == null)
            throw new UserByNameNotFoundException();
        user.setScore(dto.getScenario(),dto.getScore());
        userRepository.save(user);
    }
}
