package com.techsmithsuk;

public class Main {

    public static void main(String[] args) {
	    for (int counter = 1; counter <= 100; counter++) {
	        System.out.println(getResponse(counter));
        }
    }

    private static String getResponse(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        }

        if (number % 3 == 0) {
            return "Fizz";
        }

        if (number % 5 == 0) {
            return "Buzz";
        }

        return String.format("%d", number);
    }
}
