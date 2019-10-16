package com.techsmithsuk;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Rule fizzRule = new Rule(3, "Fizz");
        Rule buzzRule = new Rule(5, "Buzz");
        Rule bangRule = new Rule(7, "Bang");

        List<Rule> rules = Arrays.asList(fizzRule, buzzRule, bangRule);

	    for (int counter = 1; counter <= 200; counter++) {
	        System.out.println(getResponse(counter, rules));
        }
    }

    private static String getResponse(int number, List<Rule> rules) {
        if (number % 11 == 0) {
            return "Bong";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Rule rule : rules) {
            if (rule.matches(number)) {
                stringBuilder.append(rule.getLabel());
            }
        }

        if (stringBuilder.length() == 0) {
            return getNumberAsString(number);
        }

        return stringBuilder.toString();
    }

    private static String getNumberAsString(int number) {
        return String.format("%d", number);
    }
}
