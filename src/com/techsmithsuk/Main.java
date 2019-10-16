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
        Integer reverse = getReverseFactorFromArgs(args);
        IntStream.rangeClosed(1, 300)
                .mapToObj(number -> getResponse(number, rules, reverse))
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

    private static Integer getReverseFactorFromArgs(String[] args) {
        // Expects an input of the form `--reverse=17`
        for (String arg : args) {
            if (arg.startsWith("--reverse=")) {
                String value = arg.replace("--reverse=", "");
                return Integer.parseInt(value);
            }
        }
        return null;
    }

    private static String getResponse(Integer number, List<Rule> rules, Integer reverseFactor) {
        List<String> sections = rules.stream()
                .filter(rule -> rule.matches(number))
                .map(Rule::getLabel)
                .collect(Collectors.toList());

        if (reverseFactor != null && number % reverseFactor == 0) {
            Collections.reverse(sections);
        }

        return sections.isEmpty() ? number.toString() : String.join("", sections);
    }
}
