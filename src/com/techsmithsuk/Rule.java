package com.techsmithsuk;

import java.util.List;

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

    public static Rule fromString(String input) {
        // Expects an input of the form `3=>Fizz`
        String[] parts = input.split("=>");
        int factor = Integer.parseInt(parts[0]);
        String label = parts[1];

        return new Rule(factor, label);
    }
}
