package com.FireEmbelm.FireEmblem.web.controllers;

import com.FireEmbelm.FireEmblem.app.interactors.BlacksmithInteractor;
import com.FireEmbelm.FireEmblem.app.utils.AppUtils;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.exceptions.TooSmallAmountOfMoneyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@CrossOrigin
@RestController
@Validated
public class BlacksmithController {

    @Autowired
    private BlacksmithInteractor mBlacksmithInteractor;

    @Autowired
    private AppUtils mAppUtils;

    @PutMapping("/api/v1/weapon/upgrade")
    public void upgradeWeapon(
            Principal principal,
            @Valid @NotBlank @RequestParam(name = "characterName") String characterName,
            @Valid @Min(0) @RequestParam(name = "itemId") Integer itemId
    ) throws InvalidEquipmentException, TooSmallAmountOfMoneyException {
        mBlacksmithInteractor.upgradeWeapon(
                characterName,itemId, mAppUtils.getGameIdFromLogin(principal.getName())
        );
    }

}
