package com.techsmithsuk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Rule fizzRule = new Rule(3, "Fizz");
        Rule buzzRule = new Rule(5, "Buzz");
        Rule bangRule = new Rule(7, "Bang");
        Rule fezzRule = new Rule(13, "Fezz");

        List<Rule> rules = Arrays.asList(fizzRule, fezzRule, buzzRule, bangRule);

	    for (Integer counter = 1; counter <= 300; counter++) {
	        System.out.println(getResponse(counter, rules));
        }
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
