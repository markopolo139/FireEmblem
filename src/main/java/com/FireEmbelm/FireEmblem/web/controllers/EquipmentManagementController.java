package com.FireEmbelm.FireEmblem.web.controllers;

import com.FireEmbelm.FireEmblem.app.interactors.EquipmentManagementInteractor;
import com.FireEmbelm.FireEmblem.app.utils.AppUtils;
import com.FireEmbelm.FireEmblem.business.exceptions.EquipmentLimitException;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.web.models.payload.equipment.EquipPayload;
import com.FireEmbelm.FireEmblem.web.models.payload.equipment.EquipmentConvoyPayload;
import com.FireEmbelm.FireEmblem.web.models.payload.equipment.TradePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@Validated
public class EquipmentManagementController {

    @Autowired
    private EquipmentManagementInteractor mEquipmentManagementInteractor;

    @Autowired
    private AppUtils mAppUtils;

    @PutMapping("/api/v1/getEquipmentFromConvoy")
    public void getEquipmentForCharacterFromConvoy(
            Principal principal, @Valid @RequestBody EquipmentConvoyPayload equipmentConvoyPayload
    ) throws EquipmentLimitException {

        mEquipmentManagementInteractor.getEquipmentForCharacterFromConvoy(
                equipmentConvoyPayload.characterName, equipmentConvoyPayload.elementId,
                mAppUtils.getGameIdFromLogin(principal.getName())
        );

    }

    @PutMapping("/api/v1/giveEquipmentFromConvoy")
    public void giveEquipmentFromCharacterToConvoy(
            Principal principal, @Valid @RequestBody EquipmentConvoyPayload equipmentConvoyPayload
    ) {

        mEquipmentManagementInteractor.giveEquipmentFromCharacterToConvoy(
                equipmentConvoyPayload.characterName, equipmentConvoyPayload.elementId,
                mAppUtils.getGameIdFromLogin(principal.getName())
        );

    }

    @PutMapping("/api/v1/storeAllEquipment")
    public void storeAllEquipmentFromCharacters(Principal principal) {

        mEquipmentManagementInteractor.storeAllEquipmentFromCharacters(
                mAppUtils.getGameIdFromLogin(principal.getName())
        );

    }

    @PutMapping("/api/v1/trade")
    public void trade(
            Principal principal, @Valid @RequestBody TradePayload tradePayload
    ) throws EquipmentLimitException {

        mEquipmentManagementInteractor.trade(
                tradePayload.tradeFromCharacterName, tradePayload.tradeToCharacterName,
                tradePayload.equipmentId, mAppUtils.getGameIdFromLogin(principal.getName())
        );

    }

    @PutMapping("/api/v1/equipItem")
    public void equipItem(
            Principal principal, @Valid @RequestBody EquipPayload equipPayload
    ) throws InvalidEquipmentException {

        mEquipmentManagementInteractor.equipItem(
                equipPayload.characterName, equipPayload.equipmentId, mAppUtils.getGameIdFromLogin(principal.getName())
        );

    }

    @GetMapping("/api/v1/equipment/convoy")
    public List<Equipment> getItemsConvoyEquipment(Principal principal) {
        return mEquipmentManagementInteractor
                .getItemsConvoyEquipment(mAppUtils.getGameIdFromLogin(principal.getName()));
    }

    @GetMapping("/api/v1/convoy/money")
    public int getConvoyMoney(Principal principal) {
        return mEquipmentManagementInteractor
                .getConvoyMoney(mAppUtils.getGameIdFromLogin(principal.getName()));
    }

    @GetMapping("/api/v1/equipment/character")
    public List<Equipment> getCharacterEquipment(
            @Valid @NotBlank @RequestParam(name = "characterName") String characterName, Principal principal
    ) {
        return mEquipmentManagementInteractor
                .getCharacterEquipment(characterName, mAppUtils.getGameIdFromLogin(principal.getName()));
    }

}
