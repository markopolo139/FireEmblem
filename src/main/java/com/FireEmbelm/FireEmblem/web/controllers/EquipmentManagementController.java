package com.FireEmbelm.FireEmblem.web.controllers;

import com.FireEmbelm.FireEmblem.app.interactors.EquipmentManagementInteractor;
import com.FireEmbelm.FireEmblem.app.interactors.service.security.userdetail.UserDetail;
import com.FireEmbelm.FireEmblem.app.utils.AppUtils;
import com.FireEmbelm.FireEmblem.business.exceptions.EquipmentLimitException;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.web.models.payload.equipment.EquipPayload;
import com.FireEmbelm.FireEmblem.web.models.payload.equipment.EquipmentConvoyPayload;
import com.FireEmbelm.FireEmblem.web.models.payload.equipment.TradePayload;
import com.FireEmbelm.FireEmblem.web.models.request.ItemsConvoyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@CrossOrigin
@RestController
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
                equipmentConvoyPayload.characterName, equipmentConvoyPayload.convoyMoney,
                equipmentConvoyPayload.elementId, mAppUtils.getGameIdFromLogin(principal.getName())
        );

    }

    @PutMapping("/api/v1/giveEquipmentFromConvoy")
    public void giveEquipmentFromCharacterToConvoy(
            Principal principal, @Valid @RequestBody EquipmentConvoyPayload equipmentConvoyPayload
    ) {

        mEquipmentManagementInteractor.giveEquipmentFromCharacterToConvoy(
                equipmentConvoyPayload.characterName, equipmentConvoyPayload.convoyMoney,
                equipmentConvoyPayload.elementId, mAppUtils.getGameIdFromLogin(principal.getName())
        );

    }

    @PutMapping("/api/v1/storeAllEquipment")
    public void storeAllEquipmentFromCharacters(
            Principal principal, @Valid @NotNull @Min(0) @RequestBody Integer convoyMoney
    ) {

        mEquipmentManagementInteractor.storeAllEquipmentFromCharacters(
                convoyMoney, mAppUtils.getGameIdFromLogin(principal.getName())
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

}
