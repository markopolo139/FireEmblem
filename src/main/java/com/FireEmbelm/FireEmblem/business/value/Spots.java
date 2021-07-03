package com.FireEmbelm.FireEmblem.business.value;

import com.FireEmbelm.FireEmblem.business.entitie.BaseCharacter;

//TODO : remember to give position when getting from base
public enum Spots {
    FORREST(1,0,10,2),
    FORT(2,20,20,1),
    GATE(3,20,20,1),
    GRASS(0,0,0,1),
    PLAIN(0,0,0,1);

    private final int mDefBoost;
    private final int mPercentHeal;
    private final int mAvoBoost;
    private final int mMoveCost;
    private int mHeight = 0;
    private int mWidth = 0;
    private BaseCharacter mCharacterOnSpot = null;

    public int getDefBoost() {
        return mDefBoost;
    }

    public int getPercentHeal() {
        return mPercentHeal;
    }

    public int getAvoBoost() {
        return mAvoBoost;
    }

    public int getMoveCost() {
        return mMoveCost;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        this.mHeight = height;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        this.mWidth = width;
    }

    public BaseCharacter getCharacterOnSpot() {
        return mCharacterOnSpot;
    }

    public void setCharacterOnSpot(BaseCharacter characterOnSpot) {
        mCharacterOnSpot = characterOnSpot;
    }

    public Spots setPosition(int height, int width) {
        mHeight = height;
        mWidth = width;
        return this;
    }

    Spots(int defBoost, int percentHeal, int avoBoost, int moveCost) {
        mDefBoost = defBoost;
        mPercentHeal = percentHeal;
        mAvoBoost = avoBoost;
        mMoveCost = moveCost;
    }

}
