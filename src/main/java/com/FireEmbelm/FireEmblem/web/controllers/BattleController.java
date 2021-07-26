package com.FireEmbelm.FireEmblem.web.controllers;

import com.FireEmbelm.FireEmblem.app.interactors.BattleInteractor;
import com.FireEmbelm.FireEmblem.app.interactors.service.security.userdetail.UserDetail;
import com.FireEmbelm.FireEmblem.app.utils.AppUtils;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidSpotException;
import com.FireEmbelm.FireEmblem.business.exceptions.NoWeaponException;
import com.FireEmbelm.FireEmblem.business.exceptions.OutOfRangeException;
import com.FireEmbelm.FireEmblem.web.models.payload.battle.BattlePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
                battlePayload.attackerSpot,battlePayload.defenderSpot,
                battlePayload.itemsConvoyModel, mAppUtils.getGameIdFromLogin(principal.getName())
        );

    }

}
