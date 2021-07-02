package com.FireEmbelm.FireEmblem.business.value;

public enum Stats {
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

    private int mValue = 0;

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        this.mValue = value;
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
