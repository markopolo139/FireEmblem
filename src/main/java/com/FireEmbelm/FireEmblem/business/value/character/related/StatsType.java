package com.FireEmbelm.FireEmblem.business.value.character.related;

public enum StatsType {
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

    StatsType() {
    }

    public abstract String getShortName();

    public static StatsType getFromShortName(String shortName) {
        for (StatsType s : StatsType.values()) {
            if (s.getShortName().equals(shortName)) {
                return s;
            }
        }
        throw new IllegalStateException("Invalid short name");
    }
}
