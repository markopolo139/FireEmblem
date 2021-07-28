package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.CharacterConverter;
import com.FireEmbelm.FireEmblem.app.converters.EnemyConverter;
import com.FireEmbelm.FireEmblem.app.converters.SpotConverter;
import com.FireEmbelm.FireEmblem.app.converters.WeaponConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.SpotEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.EnemyRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.SpotRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.WeaponRepository;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.service.generators.EnemyGenerator;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnemyGeneratorInteractor {

    @Autowired
    private EnemyGenerator mEnemyGenerator;

    @Autowired
    private SpotRepository mSpotRepository;

    @Autowired
    private SpotConverter mSpotConverter;

    @Autowired
    private EnemyRepository mEnemyRepository;

    @Autowired
    private EnemyConverter mEnemyConverter;

    @Autowired
    private CharacterRepository mCharacterRepository;

    @Autowired
    private CharacterConverter mCharacterConverter;

    @Autowired
    private WeaponRepository mWeaponRepository;

    @Autowired
    private WeaponConverter mWeaponConverter;

    public void generateEnemies(Long gameId) {

        mSpotRepository.saveAll(
                mSpotRepository.findByEnemyIdNotNullAndGameId_GameId(gameId).stream().peek(i -> i.enemyId = null)
                        .collect(Collectors.toList())
        );

        mEnemyRepository.deleteAllByGameId_GameId(gameId);

        List<Spot> field = mSpotConverter.convertEntityListToSpot(mSpotRepository.findAll());

        Character maxLevelCharacter =
                mCharacterConverter.convertEntityToCharacter(mCharacterRepository.findFirstByGameId_GameIdOrderByLevelDesc(gameId));

        List<Spot> generatedEnemies = mEnemyGenerator.generateEnemy(
                field,
                mWeaponConverter.convertEntityListToWeapon(
                        mWeaponRepository.findAll().stream().map(i -> i.weaponEmbeddable).collect(Collectors.toList())
                ),
                maxLevelCharacter.getCharacterClass().isPromotedClass()
                        ? maxLevelCharacter.getLevel() + 20 : maxLevelCharacter.getLevel()
        );

        List<SpotEntity> fieldWithEnemies = mSpotConverter.convertListToEntity(generatedEnemies);

        for(SpotEntity se : fieldWithEnemies) {
            SpotEntity inBaseEntity =
                    mSpotRepository.findByHeightAndWidthAndGameId_GameId(se.height, se.width, gameId).orElseThrow();
            se.spotId = inBaseEntity.spotId;
            se.gameId = inBaseEntity.gameId;
            se.enemyId.gameId = inBaseEntity.gameId;
            mEnemyRepository.save(se.enemyId);
            mSpotRepository.save(se);
        }

    }
}
