package com.FireEmbelm.FireEmblem.business.value;

//TODO : remember to give stats value when getting from base
public enum Stats {
    HEALTH {
        @Override
        public String getShortName() {
            return "Hp";
        }
    },
    STRENGTH {
        @Override
        public String getShortName() {
            return "STR";
        }
    },
    MAGICK {
        @Override
        public String getShortName() {
            return "MAG";
        }
    },
    DEFENSE {
        @Override
        public String getShortName() {
            return "DEF";
        }
    },
    RESISTANCE {
        @Override
        public String getShortName() {
            return "RES";
        }
    },
    LUCK {
        @Override
        public String getShortName() {
            return "LCK";
        }
    },
    SKILL {
        @Override
        public String getShortName() {
            return "SKILL";
        }
    },
    SPEED {
        @Override
        public String getShortName() {
            return "SPD";
        }
    };

    Stats() {
    }

    private int mValue = 0;
    private int mChanceToIncrease = 0;

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        this.mValue = value;
    }

    public int getChanceToIncrease() {
        return mChanceToIncrease;
    }

    public Stats setValueAndChances(int value, int chances) {
        mValue = value;
        mChanceToIncrease = chances;
        return this;
    }

    public abstract String getShortName();

    public static Stats getFromShortName(String shortName) {
        for (Stats s : Stats.values()) {
            if (s.getShortName().equals(shortName)) {
                return s;
            }
        }
        throw new IllegalStateException("Invalid short name");
    }
}
