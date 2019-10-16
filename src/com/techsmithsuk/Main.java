package com.techsmithsuk;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Options options = new Options(args);
        IntStream.rangeClosed(1, 300)
                .mapToObj(number -> getResponse(number, options))
                .forEach(System.out::println);
    }

    private static String getResponse(Integer number, Options options) {
        List<String> sections = options.getRules().stream()
                .filter(rule -> rule.matches(number))
                .map(Rule::getLabel)
                .collect(Collectors.toList());

        if (options.getReverseFactor() != null && number % options.getReverseFactor() == 0) {
            Collections.reverse(sections);
        }

        if (options.getDominantSection() != null) {
            sections.sort((a, b) -> Comparisons.isDominant(a, b, options.getDominantSection()));
        }

        return sections.isEmpty() ? number.toString() : renderOutput(sections, options.getDominantSection());
    }

    private static String renderOutput(List<String> sections, String dominantSection) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String section : sections) {
            stringBuilder.append(section);

            if (section.equals(dominantSection)) {
                return stringBuilder.toString();
            }
        }

        return stringBuilder.toString();
    }
}
