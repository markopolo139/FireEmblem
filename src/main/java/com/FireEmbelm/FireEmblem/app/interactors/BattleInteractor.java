package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.CharacterConverter;
import com.FireEmbelm.FireEmblem.app.converters.EnemyConverter;
import com.FireEmbelm.FireEmblem.app.converters.ItemsConvoyConverter;
import com.FireEmbelm.FireEmblem.app.converters.SpotConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.ItemsConvoyEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.SpotEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.EnemyRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.ItemsConvoyRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.SpotRepository;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidSpotException;
import com.FireEmbelm.FireEmblem.business.exceptions.NoWeaponException;
import com.FireEmbelm.FireEmblem.business.exceptions.OutOfRangeException;
import com.FireEmbelm.FireEmblem.business.service.BattleService;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;
import com.FireEmbelm.FireEmblem.web.models.request.SpotModel;
import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//TODO:
// - In character entity weapons first in list
@Service
public class BattleInteractor {

    @Autowired
    private BattleService mBattleService;

    @Autowired
    private SpotConverter mSpotConverter;

    @Autowired
    private SpotRepository mSpotRepository;

    @Autowired
    private EnemyRepository mEnemyRepository;

    @Autowired
    private CharacterConverter mCharacterConverter;

    @Autowired
    private CharacterRepository mCharacterRepository;

    @Autowired
    private ItemsConvoyConverter mItemsConvoyConverter;

    @Autowired
    private ItemsConvoyRepository mItemsConvoyRepository;

    public void initialiseBattle(SpotModel attackerSpotModel, SpotModel defenderSpotModel, int convoyMoney)
            throws NoWeaponException, OutOfRangeException, InvalidSpotException {

        SpotEntity startingAttackerEntity = mSpotRepository
                .findByHeightAndWidth(attackerSpotModel.height,attackerSpotModel.width);

        SpotEntity startingDefenderEntity = mSpotRepository
                .findByHeightAndWidth(defenderSpotModel.height,defenderSpotModel.width);

        ItemsConvoyEntity itemsConvoyEntity = mItemsConvoyRepository.findByMoney(convoyMoney);

        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(itemsConvoyEntity);
        Spot attackerSpot = mSpotConverter.convertModelToSpot(attackerSpotModel);
        Spot defenderSpot = mSpotConverter.convertModelToSpot(defenderSpotModel);

        mBattleService.initialiseBattle(attackerSpot,defenderSpot,itemsConvoy);

        ItemsConvoyEntity exitingEntity = mItemsConvoyConverter.convertToEntity(itemsConvoy);
        exitingEntity.convoyId = itemsConvoyEntity.convoyId;

        SpotEntity attackerEntity = mSpotConverter.convertToEntity(attackerSpot);
        settingSpotToSave(attackerEntity, startingAttackerEntity);

        SpotEntity defenderEntity = mSpotConverter.convertToEntity(defenderSpot);
        settingSpotToSave(defenderEntity,startingDefenderEntity);

        saveCharacterOnSpot(attackerSpot,attackerEntity);
        saveCharacterOnSpot(defenderSpot,defenderEntity);

        mSpotRepository.save(attackerEntity);
        mSpotRepository.save(defenderEntity);
        mItemsConvoyRepository.save(exitingEntity);
    }

    private void settingSpotToSave(SpotEntity afterChange, SpotEntity beforeChange) {
        afterChange.spotId = beforeChange.spotId;

        if (afterChange.characterId != null)
            afterChange.characterId.characterId = beforeChange.characterId.characterId;

        if (afterChange.enemyId != null)
            afterChange.enemyId.enemyId = beforeChange.enemyId.enemyId;
    }

    private void saveCharacterOnSpot(Spot afterBattle, SpotEntity spotWithCharacter) {
        if (afterBattle.getCharacterOnSpot() instanceof Enemy) {
            mEnemyRepository.save(spotWithCharacter.enemyId);

            if(afterBattle.getCharacterOnSpot().getCharacterState().equals(CharacterState.DEAD)) {
                spotWithCharacter.enemyId = null;
            }
        }
        else {
            mCharacterRepository.save(spotWithCharacter.characterId);

            if (afterBattle.getCharacterOnSpot().getCharacterState().equals(CharacterState.DEAD))
                spotWithCharacter.characterId = null;
        }

    }
}
