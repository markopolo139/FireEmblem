package com.FireEmbelm.FireEmblem.app.configuration;

import com.FireEmbelm.FireEmblem.business.service.ShopService;
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
}
