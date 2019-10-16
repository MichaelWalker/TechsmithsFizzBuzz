package com.techsmithsuk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Options {
    private static List<Rule> DEFAULT_RULES = Arrays.asList(
            new Rule(3, "Fizz"),
            new Rule(5, "Buzz")
    );

    private List<Rule> rules;
    private Integer reverseFactor;
    private String dominantSection;
    private List<ReOrderRule> reOrderRules;

    public Options(String[] args) {
        this.rules = parseRules(args);
        this.reverseFactor = parseReverseFactor(args);
        this.dominantSection = parseDominantSection(args);
        this.reOrderRules = parseReOrderRules(args);
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Integer getReverseFactor() {
        return reverseFactor;
    }

    public String getDominantSection() {
        return dominantSection;
    }

    public List<ReOrderRule> getReorderRules() {
        return reOrderRules;
    }

    private List<ReOrderRule> parseReOrderRules(String[] args) {
        // Expects an input of the form `--re-order=FEZZ=>BUZZ:BANG:BONG`

        List<ReOrderRule> rules = new ArrayList<>();
        for (String arg : args) {
            if (arg.startsWith("--re-order=")) {
                String reorderString = arg.replace("--re-order=", "");
                rules.add(ReOrderRule.fromString(reorderString));
            }
        }
        return rules;
    }

    private List<Rule> parseRules(String[] args) {
        // Expects an input of the form `--rule=3=>FIZZ`
        List<Rule> rules = new ArrayList<>();
        for (String arg : args) {
            if (arg.startsWith("--rule=")) {
                String ruleString = arg.replace("--rule=", "");
                rules.add(Rule.fromString(ruleString));
            }
        }
        return rules.isEmpty() ? DEFAULT_RULES : rules;
    }

    private Integer parseReverseFactor(String[] args) {
        // Expects an input of the form `--reverse=17`
        for (String arg : args) {
            if (arg.startsWith("--reverse=")) {
                String value = arg.replace("--reverse=", "");
                return Integer.parseInt(value);
            }
        }
        return null;
    }

    private String parseDominantSection(String[] args) {
        // Expects an input of the form `--dominant=11`
        for (String arg : args) {
            if (arg.startsWith("--dominant=")) {
                return arg.replace("--dominant=", "");
            }
        }
        return null;
    }
}
