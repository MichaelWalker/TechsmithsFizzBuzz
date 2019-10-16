package com.techsmithsuk;

import java.util.Comparator;

public class Comparisons {
    public static int isDominant(String a, String b, String dominantSection) {
        if (dominantSection == null || dominantSection.isBlank()) {
            return 0;
        }

        if (a.equals(b)) {
            return 0;
        }

        if (a.equals(dominantSection)) {
            return -1;
        }

        if (b.equals(dominantSection)) {
            return 1;
        }

        return 0;
    }
}
