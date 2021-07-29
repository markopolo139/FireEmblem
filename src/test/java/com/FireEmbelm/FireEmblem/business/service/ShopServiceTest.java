package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.TooSmallAmountOfMoneyException;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ShopServiceTest {

    private final int MONEY = 5000;
    private final int TOO_EXPENSIVE = 6000;

    private ShopService mShopService = new ShopService();
    private ItemsConvoy mItemsConvoy;
    private ArrayList<Equipment> mRandomItems;

    @BeforeEach
    void setUp() {
        mItemsConvoy = new ItemsConvoy(
                MONEY,
                new ArrayList<>(
                        Arrays.asList(
                                new HealingItemWithUses(HealingItems.HEAL, 20),
                                new HealingItemWithUses(HealingItems.VULNERARY,3),
                                new HealingItemWithUses(HealingItems.VULNERARY,3),
                                new HealingItemWithUses(HealingItems.CONCOCTIONS,3),
                                Seals.MASTER_SEAL,
                                StatsUpItems.DEFENSE_UP,
                                new Weapon(
                                        "Bronze Sword",1,3,100, 0,
                                        0,50,1, 350, WeaponCategory.SWORD
                                ),
                                new Weapon(
                                        "Bronze Lance",1,3,90, 0,
                                        0,50,1, 350, WeaponCategory.LANCE
                                ),
                                new Weapon(
                                        "Bronze Axe",1,4,80, 0,
                                        0,50,1, 400, WeaponCategory.AXE
                                ),
                                new Weapon(
                                        "Fire",1,2,90, 0,
                                        0,50,2, 540, WeaponCategory.TOME
                                ),
                                new Weapon(
                                        "After sell can buy",1,2,90, 0,
                                        0,50,2, (int) ((TOO_EXPENSIVE - MONEY) * 1.25),
                                        WeaponCategory.TOME
                                )
                        )
                )
        );

        mRandomItems = new ArrayList<>(
                Arrays.asList(
                        new HealingItemWithUses(HealingItems.MEND, 20),
                        new HealingItemWithUses(HealingItems.ELIXIR, 1),
                        Seals.HEART_SEAL,
                        StatsUpItems.STRENGTH_UP,
                        new Weapon(
                                "Iron Sword",2,5,95, 0,
                                0,50,1, 520, WeaponCategory.SWORD
                        ),
                        new Weapon(
                                "Too expensive",2,5,95, 0,
                                0,50,1, TOO_EXPENSIVE, WeaponCategory.SWORD
                        )
                )
            );
    }

    @Test
    void buyTest() throws TooSmallAmountOfMoneyException {

        mShopService.buyItem(0, mRandomItems,mItemsConvoy);

        Assertions.assertEquals(mItemsConvoy.getMoney(), 3500);
        Assertions.assertEquals(mItemsConvoy.getPlayerItems().size(), 12);
        Assertions.assertEquals(mRandomItems.size(), 5);
        Assertions.assertEquals(
                mItemsConvoy.getPlayerItems().get(mItemsConvoy.getPlayerItems().size() - 1),
                new HealingItemWithUses(HealingItems.MEND, 20));

    }

    @Test
    void sellTest() throws TooSmallAmountOfMoneyException {

        mShopService.sellItem(10, mItemsConvoy);

        Assertions.assertEquals(mItemsConvoy.getMoney(), 6000);
        Assertions.assertEquals(mItemsConvoy.getPlayerItems().size(), 10);

        mShopService.buyItem(5,mRandomItems,mItemsConvoy);

        Assertions.assertEquals(mItemsConvoy.getPlayerItems().size(), 11);
        Assertions.assertEquals(mItemsConvoy.getMoney(), 0);
        Assertions.assertEquals(mRandomItems.size(), 5);

    }
}
