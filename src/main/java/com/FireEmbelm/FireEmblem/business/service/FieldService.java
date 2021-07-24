package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidSpotException;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;
import com.FireEmbelm.FireEmblem.business.value.equipment.HealingItemWithUses;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;

import java.util.Collection;

//TODO : test
// front end - selecting enemy to attack, selecting item to use, menu (attack, items, wait, check, move)
public class FieldService {

    public void placeCharacter(Character character, Spot spot) throws InvalidSpotException {

        if(spot.getHeight() > 2)
            throw new InvalidSpotException("Spot is too far");

        if(spot.getCharacterOnSpot() != null)
            throw new InvalidSpotException("Can't place character on another character");

        spot.setCharacterOnSpot(character);

    }

    public void moveCharacter(Spot characterSpot, Spot moveToSpot) throws InvalidSpotException {

        if(moveToSpot.getCharacterOnSpot() != null)
            throw new InvalidSpotException("Can't move to spot with character on it");

        int distance = Math.abs(characterSpot.getHeight() - moveToSpot.getHeight())
                + Math.abs(characterSpot.getWidth() - moveToSpot.getWidth());

        if(characterSpot.getCharacterOnSpot().getCharacterClass().getMovement() < distance)
            throw new InvalidSpotException("Selected spot is too far");

        moveToSpot.setCharacterOnSpot(characterSpot.getCharacterOnSpot());
        characterSpot.setCharacterOnSpot(null);

    }

    public void endTurn(Character character) {
        character.setMoved(true);
    }

    public void startTurn(Collection<Spot> charactersSpots) {

        for(Spot characterSpot : charactersSpots) {

            characterSpot.getCharacterOnSpot().setMoved(false);

            int characterMaxHp = characterSpot.getCharacterOnSpot().getStats().get(StatsType.HEALTH).getValue()
                    + characterSpot.getCharacterOnSpot().getCharacterClass().getBonusStats().get(StatsType.HEALTH).getValue();

            int spotHeal = characterSpot.getSpotsType().getPercentHeal() * characterMaxHp / 100;

            characterSpot.getCharacterOnSpot().setRemainingHealth(
                    Math.min(spotHeal + characterSpot.getCharacterOnSpot().getRemainingHealth(), characterMaxHp)
            );
        }
    }

    public void useConsumableItem(Character character, int itemId) throws InvalidEquipmentException {

        if(!StatsUpItems.class.isAssignableFrom(character.getEquipment().get(itemId).getClass()))
            throw new InvalidEquipmentException("Selected item is not an stats up item");

        StatsUpItems statsUpItems = (StatsUpItems) character.getEquipment().get(itemId);

        character.getStats().get(statsUpItems.getBoostedStat()).setValue(
                character.getStats().get(statsUpItems.getBoostedStat()).getValue() + statsUpItems.getHowMuchStatUp());

        character.getEquipment().remove(statsUpItems);

    }

    public void useHealingItem(Character character, int itemId) throws InvalidEquipmentException {

        if(!HealingItemWithUses.class.isAssignableFrom(character.getEquipment().get(itemId).getClass()))
            throw new InvalidEquipmentException("Selected item is not an healing item");

        HealingItemWithUses healingItemWithUses =  (HealingItemWithUses) character.getEquipment().get(itemId);

        if(healingItemWithUses.getItemCategory().equals(WeaponCategory.STAFF))
            throw new InvalidEquipmentException("Can't use Staff");

        int characterMaxHp = character.getStats().get(StatsType.HEALTH).getValue()
                + character.getCharacterClass().getBonusStats().get(StatsType.HEALTH).getValue();

        character.setRemainingHealth(
                Math.min(healingItemWithUses.getHealingItems().getHealValue() + character.getRemainingHealth(), characterMaxHp)
        );

        healingItemWithUses.setUses(healingItemWithUses.getUses() - 1);
        if (healingItemWithUses.getUses() == 0)
            character.getEquipment().remove(itemId);

    }

    public void useStaff(Spot healingCharacter, Spot healedCharacter, int itemId)
            throws InvalidEquipmentException, InvalidSpotException {

        if(!HealingItemWithUses.class.isAssignableFrom(
                healingCharacter.getCharacterOnSpot().getEquipment().get(itemId).getClass()
        ))
            throw new InvalidEquipmentException("Selected item is not an healing item");

        HealingItemWithUses staff = (HealingItemWithUses) healingCharacter.getCharacterOnSpot().getEquipment().get(itemId);

        if(!staff.getItemCategory().equals(WeaponCategory.STAFF))
            throw new InvalidEquipmentException("Selected item is not a staff");

        if(!healingCharacter.getCharacterOnSpot().getCharacterClass().getAllowedWeapons().contains(WeaponCategory.STAFF))
            throw new InvalidEquipmentException("This class can't use staffs");

        int distance = Math.abs(healingCharacter.getHeight() - healedCharacter.getHeight())
                + Math.abs(healingCharacter.getWidth() - healedCharacter.getWidth());

        if(distance != 1)
            throw new InvalidSpotException("Selected spot is out of staff range");

        int characterMaxHp = healedCharacter.getCharacterOnSpot().getStats().get(StatsType.HEALTH).getValue()
                + healedCharacter.getCharacterOnSpot().getCharacterClass().getBonusStats().get(StatsType.HEALTH).getValue();

        healedCharacter.getCharacterOnSpot().setRemainingHealth(
                Math.min(
                        staff.getHealingItems().getHealValue()
                                + healedCharacter.getCharacterOnSpot().getRemainingHealth(), characterMaxHp
                )
        );

        staff.setUses(staff.getUses() - 1);
        if (staff.getUses() == 0)
            healingCharacter.getCharacterOnSpot().getEquipment().remove(itemId);

    }

}
