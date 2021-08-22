package com.FireEmbelm.FireEmblem.business.value;
//TODO : add in future (add column in game table with difficulty setting)
public enum DifficultySettings {
    EASY(
            "Easy",
            0.8,
            1.2,
            1.2,
            0.9, 0.8
    ),
    NORMAL(
            "Normal",
            1,
            1,
            1,
            1,
            1
    ),
    HARD(
            "Hard",
            1.1,
            0.9,
            0.8,
            1.1,
            1.1
    );

    private final String mName;
    private final double mEnemyDamageBoost;
    private final double mExpGained;
    private final double mEnemyDamageReceived;
    private final double mEnemyHitBoost;
    private final double mEnemyAvoBoost;

    DifficultySettings(
            String name, double enemyDamageBoost, double expGained,
            double enemyDamageReceived, double enemyHitBoost, double enemyAvoBoost
    ) {
        mName = name;
        mEnemyDamageBoost = enemyDamageBoost;
        mExpGained = expGained;
        mEnemyDamageReceived = enemyDamageReceived;
        mEnemyHitBoost = enemyHitBoost;
        mEnemyAvoBoost = enemyAvoBoost;
    }

    public String getName() {
        return mName;
    }

    public double getEnemyDamageBoost() {
        return mEnemyDamageBoost;
    }

    public double getExpGained() {
        return mExpGained;
    }

    public double getEnemyDamageReceived() {
        return mEnemyDamageReceived;
    }

    public double getEnemyHitBoost() {
        return mEnemyHitBoost;
    }

    public double getEnemyAvoBoost() {
        return mEnemyAvoBoost;
    }
}
