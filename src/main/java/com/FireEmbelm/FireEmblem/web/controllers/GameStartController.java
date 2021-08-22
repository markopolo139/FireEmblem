package com.FireEmbelm.FireEmblem.web.controllers;

import com.FireEmbelm.FireEmblem.app.interactors.service.GameStartService;
import com.FireEmbelm.FireEmblem.app.utils.AppUtils;
import com.FireEmbelm.FireEmblem.web.validation.ValidDifficultySetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin
@RestController
@Validated
public class GameStartController {

    @Autowired
    private GameStartService mGameStartService;

    @Autowired
    private AppUtils mAppUtils;

    @PostMapping("/api/v1/startNewGame")
    public void startGame(
            Principal principal, @ValidDifficultySetting @RequestParam(name = "difficulty") String difficulty
    ) {
        mGameStartService.startGame(mAppUtils.getGameIdFromLogin(principal.getName()), difficulty);
    }

    @PostMapping("/api/v1/changeDifficulty")
    public void setDifficulty(
            Principal principal, @ValidDifficultySetting @RequestParam(name = "difficulty") String difficulty
    ) {
        mGameStartService.setDifficultyForGame(mAppUtils.getGameIdFromLogin(principal.getName()), difficulty);
    }

}
