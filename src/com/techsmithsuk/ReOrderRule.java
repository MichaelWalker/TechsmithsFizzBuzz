package com.techsmithsuk;


import java.util.Collection;
import java.util.List;

public class ReOrderRule {
    private String label;
    private Collection<String> goesBefore;

    public ReOrderRule(String label, Collection<String> goesBefore) {
        this.label = label;
        this.goesBefore = goesBefore;
    }

    public static ReOrderRule fromString(String reorderString) {
        String[] parts = reorderString.split("=>");
        String label = parts[0];

        String goesBeforeString = parts[1];
        Collection<String> goesBefore = List.of(goesBeforeString.split(":"));
        return new ReOrderRule(label, goesBefore);
    }

    public String getLabel() {
        return label;
    }

    public boolean goesBefore(String section) {
        return goesBefore.contains(section);
    }
}
