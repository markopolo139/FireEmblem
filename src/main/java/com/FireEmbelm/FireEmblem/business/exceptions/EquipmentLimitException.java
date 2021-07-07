package com.FireEmbelm.FireEmblem.business.exceptions;

import com.FireEmbelm.FireEmblem.business.service.EquipmentManagementService;

public class EquipmentLimitException extends Exception {
    public EquipmentLimitException() {
        super("Reached limit of equipment, which is " + EquipmentManagementService.EQUIPMENT_LIMIT);
    }
}
