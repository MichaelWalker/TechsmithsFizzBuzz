package com.techsmithsuk;

public class Rule {
    private final int factor;
    private final String label;

    public Rule(int factor, String label) {
        this.factor = factor;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public boolean matches(int number) {
        return number % this.factor == 0;
    }
}
