package com.FireEmbelm.FireEmblem.web.controllers;

import com.FireEmbelm.FireEmblem.app.interactors.FieldGeneratorInteractor;
import com.FireEmbelm.FireEmblem.app.interactors.service.security.userdetail.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin
@RestController
public class FieldGeneratorController {

    @Autowired
    private FieldGeneratorInteractor mFieldGeneratorInteractor;

    @PostMapping("/api/v1/generateField")
    public void generateField(Principal principal) {
        mFieldGeneratorInteractor.generateField(((UserDetail) principal).getGameId());
    }

}
