package com.techsmithsuk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    private static List<Rule> DEFAULT_RULES = Arrays.asList(
        new Rule(3, "Fizz"),
        new Rule(5, "Buzz"),
        new Rule(7, "Bang"),
        new Rule(13, "Fezz")
    );

    public static void main(String[] args) {
        List<Rule> rules = getRulesFromArgs(args);
        IntStream.rangeClosed(1, 300)
                .mapToObj(number -> getResponse(number, rules))
                .forEach(System.out::println);
    }

    private static List<Rule> getRulesFromArgs(String[] args) {
        // Expects an input of the form `--rules=3=>Fizz,5=>Buzz`

        List<Rule> commandLineRules = Arrays.stream(args)
                .filter(arg -> arg.startsWith("--rules="))
                .map(arg -> arg.replace("--rules=", ""))
                .flatMap(rulesString -> Stream.of(rulesString.split(",")))
                .map(Rule::fromString)
                .collect(Collectors.toList());

        return commandLineRules.isEmpty() ? DEFAULT_RULES : commandLineRules;
    }

    private static String getResponse(Integer number, List<Rule> rules) {
        List<String> sections = new ArrayList<>();

        if (number % 11 == 0) {
            if (number % 13 == 0) {
                sections.add("Fezz");
            }
            sections.add("Bong");
        }
        else {
            for (Rule rule : rules) {
                if (rule.matches(number)) {
                    sections.add(rule.getLabel());
                }
            }
        }

        if (sections.isEmpty()) {
            return number.toString();
        }

        if (number % 17 == 0) {
            Collections.reverse(sections);
        }

        return String.join("", sections);
    }
}
