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
                .sorted((a, b) -> Comparisons.isDominant(a, b, options.getDominantSection()))
                .collect(Collectors.toList());

        sections = removeEntriesAfterDominantSection(sections, options.getDominantSection());

        if (options.getReverseFactor() != null && number % options.getReverseFactor() == 0) {
            Collections.reverse(sections);
        }

        return sections.isEmpty() ? number.toString() : String.join("", sections);
    }

    private static List<String> removeEntriesAfterDominantSection(List<String> sections, String dominantSection) {
        if (dominantSection == null || dominantSection.isBlank() || !sections.contains(dominantSection)) {
            return sections;
        }
        int indexOfDominantSection = sections.indexOf(dominantSection);
        return sections.subList(0, indexOfDominantSection + 1);
    }
}
