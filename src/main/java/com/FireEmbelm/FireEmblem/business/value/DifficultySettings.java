package com.FireEmbelm.FireEmblem.business.value;
//Percentage
public enum DifficultySettings {
    EASY(
            "Easy",
            0.8,
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
            1,
            1
    ),
    HARD(
            "Hard",
            1.2,
            1.1,
            0.9,
            0.8,
            1.1,
            1.1
    );

    private final String mName;
    private final double mEnemyBoost;
    private final double mEnemyDamageBoost;
    private final double mExpGained;
    private final double mEnemyDamageReceived;
    private final double mEnemyHitBoost;
    private final double mEnemyAvoBoost;

    DifficultySettings(
            String name, double enemyBoost, double enemyDamageBoost,
            double expGained, double enemyDamageReceived, double enemyHitBoost, double enemyAvoBoost
    ) {
        mName = name;
        mEnemyBoost = enemyBoost;
        mEnemyDamageBoost = enemyDamageBoost;
        mExpGained = expGained;
        mEnemyDamageReceived = enemyDamageReceived;
        mEnemyHitBoost = enemyHitBoost;
        mEnemyAvoBoost = enemyAvoBoost;
    }

    public String getName() {
        return mName;
    }

    public double getEnemyBoost() {
        return mEnemyBoost;
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
