package com.FireEmbelm.FireEmblem.business.value.Field;

import com.FireEmbelm.FireEmblem.business.entitie.BaseCharacter;

public class Spot {

    private SpotsType mSpotsType;
    private int mHeight = 0;
    private int mWidth = 0;
    private BaseCharacter mCharacterOnSpot = null;

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

    public int getHeight() {
        return mHeight;
    }

    public SpotsType getSpotsType() {
        return mSpotsType;
    }

    public Spot(SpotsType spotsType, int height, int width, BaseCharacter characterOnSpot) {
        mSpotsType = spotsType;
        mHeight = height;
        mWidth = width;
        mCharacterOnSpot = characterOnSpot;
    }
}
