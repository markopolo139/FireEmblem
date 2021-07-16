package com.FireEmbelm.FireEmblem.app.configuration;

import com.FireEmbelm.FireEmblem.business.service.*;
import com.FireEmbelm.FireEmblem.business.service.generators.EnemyGenerator;
import com.FireEmbelm.FireEmblem.business.service.generators.FieldGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//TODO : to create shopService use method to generate random items from interactor, and getting all items from base
@Configuration
public class BusinessConfiguration {

    @Bean
    @Scope("Singleton")
    public ShopService getShopService() {
        return new ShopService();
    }

    @Bean
    @Scope("Singleton")
    public FieldGenerator getFieldGenerator() {
        return new FieldGenerator();
    }

    @Bean
    @Scope("Singleton")
    public BattleService getBattleService() {
        return new BattleService(getCharacterDevelopmentService());
    }

    @Bean
    @Scope("Singleton")
    public EnemyGenerator getEnemyGenerator() {
        return new EnemyGenerator();
    }

    @Bean
    @Scope("Singleton")
    public FieldService getBoardService() {
        return new FieldService();
    }

    @Bean
    @Scope("Singleton")
    public CharacterDevelopmentService getCharacterDevelopmentService() {
        return new CharacterDevelopmentService();
    }

    @Bean
    @Scope("Singleton")
    public EquipmentManagementService getEquipmentManagementService() {
        return new EquipmentManagementService();
    }

    @Bean
    @Scope("Singleton")
    public UnitPromoteService getUnitPromoteService() {
        return new UnitPromoteService();
    }

}
