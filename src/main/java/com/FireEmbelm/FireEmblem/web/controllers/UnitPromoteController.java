package com.FireEmbelm.FireEmblem.web.controllers;

import com.FireEmbelm.FireEmblem.app.interactors.FieldInteractor;
import com.FireEmbelm.FireEmblem.app.interactors.UnitPromoteInteractor;
import com.FireEmbelm.FireEmblem.app.utils.AppUtils;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.exceptions.CharacterLevelException;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.exceptions.PromoteException;
import com.FireEmbelm.FireEmblem.web.models.payload.unitPromote.GetClassPayload;
import com.FireEmbelm.FireEmblem.web.models.payload.unitPromote.PromoteCharacterPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class UnitPromoteController {

    @Autowired
    private UnitPromoteInteractor mUnitPromoteInteractor;

    @Autowired
    private AppUtils mAppUtils;

    @GetMapping("/api/v1/classToPromote")
    public List<CharacterClass> getPossibleClassesToPromote(
            @Valid @RequestBody GetClassPayload getClassPayload
    ) throws InvalidEquipmentException, CharacterLevelException, PromoteException {
        return mUnitPromoteInteractor.getPossibleClassesToPromote(
                getClassPayload.characterModel,getClassPayload.seals
        );
    }

    @PutMapping("/api/v1/promoteCharacter")
    public void promoteCharacter(
            @Valid @RequestBody PromoteCharacterPayload promoteCharacterPayload,
            Principal principal
    ) {
        mUnitPromoteInteractor.promoteCharacter(
                promoteCharacterPayload.characterModel,
                promoteCharacterPayload.characterClassList,
                promoteCharacterPayload.listId,
                mAppUtils.getGameIdFromLogin(principal.getName())
        );
    }

}
