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
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.service.generators.EnemyGenerator;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//TODO:
// - In character entity weapons first in list
// - In field check if move from have character
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

    public void generateEnemies() {

        mSpotRepository.saveAll(
                mSpotRepository.findByEnemyIdNotNull().stream().peek(i -> i.enemyId = null).collect(Collectors.toList())
        );

        mEnemyRepository.deleteAll();

        List<Spot> field = mSpotConverter.convertEntityListToSpot(mSpotRepository.findAll());

        Character maxLevelCharacter =
                mCharacterConverter.convertEntityToCharacter(mCharacterRepository.findFirstByOrderByLevelDesc());

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
            se.spotId = mSpotRepository.findByHeightAndWidth(se.height, se.width).spotId;
            mEnemyRepository.save(se.enemyId);
            mSpotRepository.save(se);
        }

    }
}
