package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.internal.BaseCharacterToCharacterConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.GameEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.ItemsConvoyEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponProgressEmbeddable;
import com.FireEmbelm.FireEmblem.app.data.repository.BaseCharacterRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.GameRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.ItemsConvoyRepository;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameStartService {

    public static final int START_MONEY = 3000;

    @Autowired
    private BaseCharacterRepository mBaseCharacterRepository;

    @Autowired
    private CharacterRepository mCharacterRepository;

    @Autowired
    private ItemsConvoyRepository mItemsConvoyRepository;

    @Autowired
    private BaseCharacterToCharacterConverter mBaseCharacterToCharacterConverter;

    @Autowired
    private GameRepository mGameRepository;

    public void startGame(Long gameId) {

        GameEntity gameEntity = mGameRepository.findById(gameId).orElseThrow();

        mCharacterRepository.deleteAllByGameId_GameId(gameId);
        mItemsConvoyRepository.deleteByGameId_GameId(gameId);

        List<CharacterEntity> characterEntities =
                mBaseCharacterToCharacterConverter.convertListToCharacterEntity(mBaseCharacterRepository.findAll());

        characterEntities.forEach(i -> giveBasicWeaponProgressAndGameEntity(i, gameEntity));

        ItemsConvoyEntity itemsConvoyToSave = new ItemsConvoyEntity(
                1L,START_MONEY,null,null,null,null);
        itemsConvoyToSave.gameId = gameEntity;

        mCharacterRepository.saveAll(characterEntities);
        mItemsConvoyRepository.save(itemsConvoyToSave);
    }

    private void giveBasicWeaponProgressAndGameEntity(CharacterEntity characterEntity, GameEntity gameEntity) {

        characterEntity.gameId = gameEntity;

        characterEntity.weaponProgress = new ArrayList<>() {{
            add(new WeaponProgressEmbeddable(WeaponCategory.SWORD, 0, 1));
            add(new WeaponProgressEmbeddable(WeaponCategory.AXE, 0, 1));
            add(new WeaponProgressEmbeddable(WeaponCategory.LANCE, 0, 1));
            add(new WeaponProgressEmbeddable(WeaponCategory.BOW, 0, 1));
            add(new WeaponProgressEmbeddable(WeaponCategory.TOME, 0, 1));
            add(new WeaponProgressEmbeddable(WeaponCategory.STAFF, 0, 1));
        }};
    }

}
