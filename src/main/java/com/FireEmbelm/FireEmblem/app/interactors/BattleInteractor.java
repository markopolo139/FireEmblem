package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.CharacterConverter;
import com.FireEmbelm.FireEmblem.app.converters.ItemsConvoyConverter;
import com.FireEmbelm.FireEmblem.app.converters.SpotConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.ItemsConvoyEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.SpotEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.EnemyRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.ItemsConvoyRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.SpotRepository;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidSpotException;
import com.FireEmbelm.FireEmblem.business.exceptions.NoWeaponException;
import com.FireEmbelm.FireEmblem.business.exceptions.OutOfRangeException;
import com.FireEmbelm.FireEmblem.business.service.BattleService;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;
import com.FireEmbelm.FireEmblem.web.models.request.ItemsConvoyModel;
import com.FireEmbelm.FireEmblem.web.models.request.SpotModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void initialiseBattle(
            int attackerHeight, int attackerWidth, int defenderHeight, int defenderWidth, Long gameId
    )
            throws NoWeaponException, OutOfRangeException, InvalidSpotException {

        SpotEntity startingAttackerEntity = mSpotRepository
                .findByHeightAndWidthAndGameId_GameId(attackerHeight, attackerWidth, gameId);

        SpotEntity startingDefenderEntity = mSpotRepository
                .findByHeightAndWidthAndGameId_GameId(defenderHeight, defenderWidth, gameId);

        ItemsConvoyEntity itemsConvoyEntity = mItemsConvoyRepository.findByGameId_GameId(gameId);

        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(itemsConvoyEntity);
        Spot attackerSpot = mSpotConverter.convertEntityToSpot(startingAttackerEntity);
        Spot defenderSpot = mSpotConverter.convertEntityToSpot(startingDefenderEntity);

        mBattleService.initialiseBattle(attackerSpot,defenderSpot,itemsConvoy);

        ItemsConvoyEntity exitingEntity = mItemsConvoyConverter.convertToEntity(itemsConvoy);
        exitingEntity.convoyId = itemsConvoyEntity.convoyId;
        exitingEntity.gameId = itemsConvoyEntity.gameId;

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
        afterChange.gameId = beforeChange.gameId;

        if (afterChange.characterId != null) {
            afterChange.characterId.characterId = beforeChange.characterId.characterId;
            afterChange.characterId.gameId = beforeChange.characterId.gameId;
        }

        if (afterChange.enemyId != null) {
            afterChange.enemyId.enemyId = beforeChange.enemyId.enemyId;
            afterChange.enemyId.gameId = beforeChange.enemyId.gameId;
        }
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
