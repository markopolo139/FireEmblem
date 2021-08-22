package com.FireEmbelm.FireEmblem.web.controllers;

import com.FireEmbelm.FireEmblem.app.interactors.service.GameStartService;
import com.FireEmbelm.FireEmblem.app.utils.AppUtils;
import com.FireEmbelm.FireEmblem.web.validation.ValidDifficultySetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin
@RestController
public class GameStartController {

    @Autowired
    private GameStartService mGameStartService;

    @Autowired
    private AppUtils mAppUtils;

    @PostMapping("/api/v1/startNewGame")
    public void startGame(
            Principal principal, @Valid @ValidDifficultySetting @RequestParam(name = "difficulty") String difficulty
    ) {
        mGameStartService.startGame(mAppUtils.getGameIdFromLogin(principal.getName()), difficulty);
    }

}
