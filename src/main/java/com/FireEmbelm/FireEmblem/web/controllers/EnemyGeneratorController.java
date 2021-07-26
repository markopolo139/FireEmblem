package com.FireEmbelm.FireEmblem.web.controllers;

import com.FireEmbelm.FireEmblem.app.interactors.EnemyGeneratorInteractor;
import com.FireEmbelm.FireEmblem.app.interactors.service.security.userdetail.UserDetail;
import com.FireEmbelm.FireEmblem.app.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin
@RestController
public class EnemyGeneratorController {

    @Autowired
    private EnemyGeneratorInteractor mEnemyGeneratorInteractor;

    @Autowired
    private AppUtils mAppUtils;

    @PostMapping("/api/v1/generateEnemy")
    public void generateEnemy(Principal principal) {
        mEnemyGeneratorInteractor.generateEnemies(mAppUtils.getGameIdFromLogin(principal.getName()));
    }

}
