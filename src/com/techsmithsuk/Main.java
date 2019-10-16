package com.techsmithsuk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Options options = new Options(args);
        IntStream.rangeClosed(1, 300)
                .mapToObj(number -> getResponse(number, options))
                .forEach(System.out::println);
    }

    private static String getResponse(Integer number, Options options) {
        List<String> sections = new ArrayList<>();
        for (Rule rule : options.getRules()) {
            if (rule.matches(number)) {
                sections.add(rule.getLabel());
            }
        }

        applyDominantSectionRules(sections, options.getDominantSection());
        applyReOrderRules(sections, options.getReorderRules());
        removeEntriesAfterDominantSection(sections, options.getDominantSection());
        applyReverseRules(sections, number, options.getReverseFactors());

        return sections.isEmpty() ? number.toString() : String.join("", sections);
    }

    private static void applyDominantSectionRules(List<String> sections, String dominantSection) {
        if (sections.contains(dominantSection)) {
            sections.remove(dominantSection);
            sections.add(0, dominantSection);
        }
    }

    private static void applyReverseRules(List<String> sections, Integer number, List<Integer> reverseFactors) {
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

    private static void removeEntriesAfterDominantSection(List<String> sections, String dominantSection) {
        if (dominantSection == null || dominantSection.isBlank() || !sections.contains(dominantSection)) {
            return;
        }
        int indexOfDominantSection = sections.indexOf(dominantSection);
        sections.subList(indexOfDominantSection + 1, sections.size()).clear();
    }
}
