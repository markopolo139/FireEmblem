package com.FireEmbelm.FireEmblem.web.controllers;

import com.FireEmbelm.FireEmblem.app.exceptions.CharacterAlreadyMovedException;
import com.FireEmbelm.FireEmblem.app.interactors.FieldInteractor;
import com.FireEmbelm.FireEmblem.app.utils.AppUtils;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidSpotException;
import com.FireEmbelm.FireEmblem.web.models.payload.field.MovePayload;
import com.FireEmbelm.FireEmblem.web.models.payload.field.PlacePayload;
import com.FireEmbelm.FireEmblem.web.models.payload.field.UseItemPayload;
import com.FireEmbelm.FireEmblem.web.models.payload.field.UseStaffPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.Principal;

@CrossOrigin
@RestController
public class FieldController {

    @Autowired
    private FieldInteractor mFieldInteractor;

    @Autowired
    private AppUtils mAppUtils;

    @PutMapping("/api/v1/character/place")
    public void placeCharacter(Principal principal, @Valid @RequestBody PlacePayload placePayload)
            throws InvalidSpotException {

        mFieldInteractor.placeCharacter(
                placePayload.characterName,
                placePayload.spotHeight, placePayload.spotWidth,
                mAppUtils.getGameIdFromLogin(principal.getName())
        );

    }

    @PutMapping("/api/v1/character/move")
    public void moveCharacter(Principal principal, @Valid @RequestBody MovePayload movePayload)
            throws CharacterAlreadyMovedException, InvalidSpotException {

        mFieldInteractor.moveCharacter(
                movePayload.moveFromSpotHeight, movePayload.moveFromSpotWidth,
                movePayload.moveToSpotHeight, movePayload.moveToSpotWidth,
                mAppUtils.getGameIdFromLogin(principal.getName())
        );

    }

    @PutMapping("/api/v1/turn/end")
    public void endTurn(
            Principal principal, @Valid @NotBlank @RequestParam(name = "characterName") String characterName
    ) throws CharacterAlreadyMovedException {

        mFieldInteractor.endTurn(
                characterName, mAppUtils.getGameIdFromLogin(principal.getName())
        );

    }

    @PutMapping("/api/v1/turn/start")
    public void startTurn(Principal principal) {
        mFieldInteractor.startTurn(mAppUtils.getGameIdFromLogin(principal.getName()));
    }

    @PutMapping("/api/v1/use/consumable")
    public void useConsumableItem(Principal principal, @Valid @RequestBody UseItemPayload useItemPayload)
            throws CharacterAlreadyMovedException, InvalidEquipmentException {

        mFieldInteractor.useConsumableItem(
                useItemPayload.characterName,
                useItemPayload.itemId,
                mAppUtils.getGameIdFromLogin(principal.getName())
        );

    }

    @PutMapping("/api/v1/use/healing")
    public void useHealingItem(Principal principal, @Valid @RequestBody UseItemPayload useItemPayload)
            throws CharacterAlreadyMovedException, InvalidEquipmentException {

        mFieldInteractor.useHealingItem(
                useItemPayload.characterName,
                useItemPayload.itemId,
                mAppUtils.getGameIdFromLogin(principal.getName())
        );

    }

    @PutMapping("/api/v1/use/staff")
    public void useStaff(Principal principal, @Valid @RequestBody UseStaffPayload useStaffPayload)
            throws CharacterAlreadyMovedException, InvalidEquipmentException, InvalidSpotException {

        mFieldInteractor.useStaff(
                useStaffPayload.healingSpotHeight, useStaffPayload.healingSpotWidth,
                useStaffPayload.healedSpotHeight, useStaffPayload.healedSpotWidth,
                useStaffPayload.itemId,
                mAppUtils.getGameIdFromLogin(principal.getName())
        );

    }
}
