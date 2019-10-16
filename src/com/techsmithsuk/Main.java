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

        applyReOrderRules(sections, options.getReorderRules());
        sections = removeEntriesAfterDominantSection(sections, options.getDominantSection());
        reverseSectionsIfRequired(sections, number, options.getReverseFactors());

        return sections.isEmpty() ? number.toString() : String.join("", sections);
    }

    private static void reverseSectionsIfRequired(List<String> sections, Integer number, List<Integer> reverseFactors) {
        for (Integer factor : reverseFactors) {
            if (number % factor == 0) {
                Collections.reverse(sections);
                return;
            }
        }
    }

    private static void applyReOrderRules(List<String> sections, List<ReOrderRule> reorderRules) {
        for (ReOrderRule reOrderRule : reorderRules) {
            if (sections.contains(reOrderRule.getLabel())) {
                reOrderSection(sections, reOrderRule);
            }
        }
    }

    private static void reOrderSection(List<String> sections, ReOrderRule rule) {
        sections.remove(rule.getLabel());

        for (int index = 0; index < sections.size(); index++) {
            String section = sections.get(index);
            if (rule.goesBefore(section)) {
                sections.add(index, rule.getLabel());
                return;
            }
        }
        sections.add(rule.getLabel());
    }

    private static List<String> removeEntriesAfterDominantSection(List<String> sections, String dominantSection) {
        if (dominantSection == null || dominantSection.isBlank() || !sections.contains(dominantSection)) {
            return sections;
        }
        int indexOfDominantSection = sections.indexOf(dominantSection);
        return sections.subList(0, indexOfDominantSection + 1);
    }
}
