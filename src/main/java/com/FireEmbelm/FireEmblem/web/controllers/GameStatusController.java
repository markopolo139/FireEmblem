package com.FireEmbelm.FireEmblem.web.controllers;

import com.FireEmbelm.FireEmblem.app.converters.CharacterConverter;
import com.FireEmbelm.FireEmblem.app.converters.EnemyConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.EnemyEntity;
import com.FireEmbelm.FireEmblem.app.interactors.ShopInteractor;
import com.FireEmbelm.FireEmblem.app.interactors.service.GameStatusService;
import com.FireEmbelm.FireEmblem.app.utils.AppUtils;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.EnemyModel;
import com.FireEmbelm.FireEmblem.web.models.request.SpotModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class GameStatusController {

    @Autowired
    private GameStatusService mGameStatusService;

    @Autowired
    private AppUtils mAppUtils;

    @GetMapping("/api/v1/alive/enemies")
    public List<EnemyModel> getAliveEnemies(Principal principal) {
        return mGameStatusService.getAliveEnemies(mAppUtils.getGameIdFromLogin(principal.getName()));
    }

    @GetMapping("/api/v1/alive/characters")
    public List<CharacterModel> getAliveCharacters(Principal principal) {
        return mGameStatusService.getAliveCharacters(mAppUtils.getGameIdFromLogin(principal.getName()));
    }

    @GetMapping("/api/v1/notMoved/characters")
    public List<CharacterModel> getNotMovedCharacters(Principal principal) {
        return mGameStatusService.getNotMovedCharacters(mAppUtils.getGameIdFromLogin(principal.getName()));
    }

    @GetMapping("/api/v1/autoEndTurn")
    public boolean autoEndTurn(Principal principal) {
        return mGameStatusService.autoEndTurn(mAppUtils.getGameIdFromLogin(principal.getName()));
    }

    @GetMapping("/api/v1/fieldWon")
    public boolean isFieldWon(Principal principal) {
        return mGameStatusService.isFieldWon(mAppUtils.getGameIdFromLogin(principal.getName()));
    }

    @GetMapping("/api/v1/playerDefeated")
    public boolean isPlayerDefeated(Principal principal) {
        return mGameStatusService.isPlayerDefeated(mAppUtils.getGameIdFromLogin(principal.getName()));
    }

    @GetMapping("/api/v1/maxLevel")
    public int maxLevelCharacterInGame(Principal principal) {
        return mGameStatusService.maxLevelCharacterInGame(mAppUtils.getGameIdFromLogin(principal.getName()));
    }

    @GetMapping("/api/v1/getField")
    public List<SpotModel> getCurrentField(Principal principal) {
        return mGameStatusService.getCurrentField(mAppUtils.getGameIdFromLogin(principal.getName()));
    }

}
