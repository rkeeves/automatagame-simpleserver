package com.deiksoftdev.automatagame.web.rest;

import com.deiksoftdev.automatagame.dto.UserScoreUpdateDTO;
import com.deiksoftdev.automatagame.dto.UserScoresDTO;
import com.deiksoftdev.automatagame.exception.UserByNameNotFoundException;
import com.deiksoftdev.automatagame.service.UserScoreService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scores")
@RequiredArgsConstructor
public class UserScoreREST {

    Logger logger = LoggerFactory.getLogger(UserScoreREST.class);

    private final UserScoreService userScoreService;

    @GetMapping(value = "/{userName}",produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserScoresDTO> getScores(@PathVariable("userName") String userName) {
        try {
            return new ResponseEntity<>(userScoreService.getScores(userName),HttpStatus.OK);
        } catch (UserByNameNotFoundException e) {
            return new ResponseEntity<>(userScoreService.createEmptyUserScoreUpdateDTO(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public void updateScores(@PathVariable("userName") String userName, @RequestBody UserScoreUpdateDTO dto) {
        try {
            logger.info("REST PUT request with body {}",dto);
            userScoreService.update(userName,dto);
        } catch (UserByNameNotFoundException e) {
            logger.warn("REST PUT request tried to update scores by a non-existing username");
        }
    }
}
