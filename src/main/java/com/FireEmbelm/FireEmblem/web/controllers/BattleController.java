package com.FireEmbelm.FireEmblem.web.controllers;

import com.FireEmbelm.FireEmblem.app.interactors.BattleInteractor;
import com.FireEmbelm.FireEmblem.app.utils.AppUtils;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidSpotException;
import com.FireEmbelm.FireEmblem.business.exceptions.NoWeaponException;
import com.FireEmbelm.FireEmblem.business.exceptions.OutOfRangeException;
import com.FireEmbelm.FireEmblem.web.models.payload.battle.BattlePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin
@RestController
public class BattleController {

    @Autowired
    private BattleInteractor mBattleInteractor;

    @Autowired
    private AppUtils mAppUtils;

    @PutMapping("/api/v1/initialiseBattle")
    public void initialiseBattle(
            Principal principal, @Valid @RequestBody BattlePayload battlePayload
    ) throws NoWeaponException, OutOfRangeException, InvalidSpotException {

        mBattleInteractor.initialiseBattle(
                battlePayload.attackerSpotHeight,battlePayload.attackerSpotWidth,
                battlePayload.defenderSpotHeight,battlePayload.defenderSpotWidth,
                mAppUtils.getGameIdFromLogin(principal.getName())
        );

    }

}
