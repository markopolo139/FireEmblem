package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.BaseCharacter;
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

    public void startTurn(Collection<Spot> charactersSpots) {

        for(Spot characterSpot : charactersSpots) {

            BaseCharacter characterOnSpot = characterSpot.getCharacterOnSpot();
            characterOnSpot.setMoved(false);

            int characterMaxHp = characterOnSpot.getStats().get(StatsType.HEALTH).getValue()
                    + characterOnSpot.getCharacterClass().getBonusStats().get(StatsType.HEALTH).getValue();

            int spotHeal = characterSpot.getSpotsType().getPercentHeal() * characterMaxHp / 100;

            characterOnSpot.setRemainingHealth(
                    Math.min(spotHeal + characterOnSpot.getRemainingHealth(), characterMaxHp)
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
        character.setMoved(true);

    }

    public void useHealingItem(Character character, int itemId) throws InvalidEquipmentException {

        if(!HealingItemWithUses.class.isAssignableFrom(character.getEquipment().get(itemId).getClass()))
            throw new InvalidEquipmentException("Selected item is not an healing item");

        HealingItemWithUses healingItemWithUses =  (HealingItemWithUses) character.getEquipment().get(itemId);

        if(healingItemWithUses.getItemCategory().equals(WeaponCategory.STAFF))
            throw new InvalidEquipmentException("Can't use Staff");

        healAndUpdateItem(itemId, character, character, healingItemWithUses);

    }

    public void useStaff(Spot healingCharacterSpot, Spot healedCharacterSpot, int itemId)
            throws InvalidEquipmentException, InvalidSpotException {

        BaseCharacter healingCharacter = healingCharacterSpot.getCharacterOnSpot();
        BaseCharacter healedCharacter = healedCharacterSpot.getCharacterOnSpot();

        if(!HealingItemWithUses.class.isAssignableFrom(
                healingCharacter.getEquipment().get(itemId).getClass()
        ))
            throw new InvalidEquipmentException("Selected item is not an healing item");

        HealingItemWithUses staff = (HealingItemWithUses) healingCharacter.getEquipment().get(itemId);

        if(!staff.getItemCategory().equals(WeaponCategory.STAFF))
            throw new InvalidEquipmentException("Selected item is not a staff");

        if(!healingCharacter.getCharacterClass().getAllowedWeapons().contains(WeaponCategory.STAFF))
            throw new InvalidEquipmentException("This class can't use staffs");

        int distance = Math.abs(healingCharacterSpot.getHeight() - healedCharacterSpot.getHeight())
                + Math.abs(healingCharacterSpot.getWidth() - healedCharacterSpot.getWidth());

        if(distance != 1)
            throw new InvalidSpotException("Selected spot is out of staff range");

        healAndUpdateItem(itemId, healingCharacter, healedCharacter, staff);

    }

    private void healAndUpdateItem(
            int itemId, BaseCharacter healingCharacter, BaseCharacter healedCharacter, HealingItemWithUses healingItem
    ) {
        int characterMaxHp = healedCharacter.getStats().get(StatsType.HEALTH).getValue()
                + healedCharacter.getCharacterClass().getBonusStats().get(StatsType.HEALTH).getValue();

        healedCharacter.setRemainingHealth(
                Math.min(
                        healingItem.getHealingItems().getHealValue() + healedCharacter.getRemainingHealth(),
                        characterMaxHp
                )
        );

        healingItem.setUses(healingItem.getUses() - 1);
        if (healingItem.getUses() == 0)
            healingCharacter.getEquipment().remove(itemId);

        healingCharacter.setMoved(true);
    }

}
