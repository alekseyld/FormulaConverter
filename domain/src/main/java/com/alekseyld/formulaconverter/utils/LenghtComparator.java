package com.alekseyld.formulaconverter.utils;

/**
 * Created by Alekseyld on 27.09.2017.
 */

public class LenghtComparator implements java.util.Comparator<String> {
    public int compare(String o1, String o2) {
        if (o1.length() > o2.length()) {
            return -1;
        } else if (o1.length() < o2.length()) {
            return 1;
        } else {
            return 0;
        }
    }
}
