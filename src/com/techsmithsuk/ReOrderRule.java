package com.techsmithsuk;


import java.util.Collection;

public class ReOrderRule {
    private String label;
    private Collection<String> goesBefore;

    public ReOrderRule(String label, Collection<String> goesBefore) {
        this.label = label;
        this.goesBefore = goesBefore;
    }

    public String getLabel() {
        return label;
    }

    public boolean goesBefore(String section) {
        return goesBefore.contains(section);
    }
}
