package com.FireEmbelm.FireEmblem.app.configuration;

import com.FireEmbelm.FireEmblem.business.service.*;
import com.FireEmbelm.FireEmblem.business.service.generators.EnemyGenerator;
import com.FireEmbelm.FireEmblem.business.service.generators.FieldGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BusinessConfiguration {

    @Bean
    @Scope("singleton")
    public ShopService getShopService() {
        return new ShopService();
    }

    @Bean
    @Scope("singleton")
    public FieldGenerator getFieldGenerator() {
        return new FieldGenerator();
    }

    @Bean
    @Scope("singleton")
    public BattleService getBattleService() {
        return new BattleService(getCharacterDevelopmentService());
    }

    @Bean
    @Scope("singleton")
    public EnemyGenerator getEnemyGenerator() {
        return new EnemyGenerator();
    }

    @Bean
    @Scope("singleton")
    public FieldService getBoardService() {
        return new FieldService();
    }

    @Bean
    @Scope("singleton")
    public CharacterDevelopmentService getCharacterDevelopmentService() {
        return new CharacterDevelopmentService();
    }

    @Bean
    @Scope("singleton")
    public EquipmentManagementService getEquipmentManagementService() {
        return new EquipmentManagementService();
    }

    @Bean
    @Scope("singleton")
    public UnitPromoteService getUnitPromoteService() {
        return new UnitPromoteService();
    }

    @Bean
    @Scope("singleton")
    public BlacksmithService getBlacksmithService() {
        return new BlacksmithService();
    }

}
