package com.FireEmbelm.FireEmblem.business.utils;

import com.FireEmbelm.FireEmblem.business.exceptions.InvalidRankException;

public class Utils {

    public static char changeToCharRank(int rank) throws InvalidRankException {
        switch (rank) {
            case 1 :
                return 'E';
            case 2 :
                return 'D';
            case 3 :
                return 'C';
            case 4 :
                return 'B';
            case 5 :
                return 'A';
            case 6 :
                return 'S';
            default:
                throw new InvalidRankException();
        }
    }
}
