package com.FireEmbelm.FireEmblem.business.value;

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

    public void setPosition(int height, int width) {
        mHeight = height;
        mWidth = width;
    }

    Spots(int defBoost, int percentHeal, int avoBoost, int moveCost) {
        mDefBoost = defBoost;
        mPercentHeal = percentHeal;
        mAvoBoost = avoBoost;
        mMoveCost = moveCost;
    }

}
