package student;    // DO NOT REMOVE FROM SUBMITTED FILE

import java.util.*;

/**
 You are working for a promising new stock trading startup called "AlgoStreet."

 You have been tasked with developing a new trading signal that will be incorporated into the
 automatic trading strategy. A new metric has been introduced called

 positive stock pressure, which measures how many consecutive days before today (not includ-
 ing today) have a higher price.

 You will be given daily stock prices for the last N days and must return the list of daily positive
 stock pressures for each day.

 Below is an example for 1 week of data.

 |   Price   | Stock Pressure
 ------------------------------
 | Day 1 |   100     |     1
 ------------------------------
 | Day 2 |   90      |     2
 ------------------------------
 | Day 3 |   95      |     1
 ------------------------------
 | Day 4 |   100     |     1
 -------------------------------
 | Day 5 |   105     |     1
 -------------------------------
 | Day 6 |   110     |     1
 -------------------------------
 | Day 7 |   80      |     7
 -------------------------------

 Implement the function to compute stock pressure.

 To compute positive stock pressure for each stock price, you should find the last day with the
 lower (or equal) price. In other words, the positive stock pressure is the number of days since the
 last day with a lower or equal price.

 You must solve this problem by using a stack as the primary data structure.

 Code Author: Adrian Everardo Ortiz

 Running Time Analysis of compute_pressure
 --------------------
 The algorithm has a time complexity of O(n), where n is the number of days in the stockHistory.
 This is because each day's stock price is pushed onto the stack once and popped at most once.
 Thus, each price contributes constant time operations leading to a linear time complexity.

 */


public class AlgoStreet {
    public static List<Integer> computePressure(List<Integer> stockHistory) {
        // Stack to keep track of the days
        Stack<Integer> stack = new Stack<>();
        List<Integer> pressures = new ArrayList<>();

        for (int day = 0; day < stockHistory.size(); day++) {
            int currentPrice = stockHistory.get(day);

            // Pop from the stack until we find a day with a price strictly lower than the current price
            while (!stack.isEmpty() && stockHistory.get(stack.peek()) > currentPrice) {
                stack.pop();
            }

            int lastSmallerOrEqualDay = stack.isEmpty() ? 0 : stack.peek() + 1;
            pressures.add(day - lastSmallerOrEqualDay + 1);

            // Push the current day onto the stack
            stack.push(day);
        }

        return pressures;
    }

    /*
    DO NOT EDIT BELOW THIS
    Below is the unit testing suite for this file.
    It provides all the tests that your code must pass to get full credit.
    */
    public static void runUnitTests() {
        testExample();
        test2();
        test3();
        testNoDaysProvided();
        testLargeList();
        testRepeatingPrices();
    }

    public static void printTestResult(String testName, boolean result) {
        String color = result ? "\033[92m" : "\033[91m";
        String reset = "\033[0m";
        System.out.println(color + "[" + result + "] " + testName + reset);
    }

    public static void testAnswer(String testName, List<Integer> result, List<Integer> expected) {
        if (result.equals(expected)) {
            printTestResult(testName, true);
        } else {
            printTestResult(testName, false);
            System.out.println("Expected: " + expected);
            System.out.println("Got:      " + result);
        }
    }

    public static void testExample() {
        List<Integer> stockHistory = Arrays.asList(100, 90, 95, 100, 105, 110, 80);

        List<Integer> result = computePressure(stockHistory);
        List<Integer> expectedAnswer = Arrays.asList(1, 2, 1, 1, 1, 1, 7);

        testAnswer("testExample", result, expectedAnswer);
    }

    public static void test2() {
        List<Integer> stockHistory = Arrays.asList(80, 74, 75, 90, 120, 81);

        List<Integer> result = computePressure(stockHistory);
        List<Integer> expectedAnswer = Arrays.asList(1, 2, 1, 1, 1, 3);

        testAnswer("test2", result, expectedAnswer);
    }

    public static void test3() {
        List<Integer> stockHistory = Arrays.asList(1, 2, 5, 10, 12, 20);

        List<Integer> result = computePressure(stockHistory);
        List<Integer> expectedAnswer = Arrays.asList(1, 1, 1, 1, 1, 1);

        testAnswer("test3", result, expectedAnswer);
    }

    public static void testNoDaysProvided() {
        List<Integer> stockHistory = new ArrayList<>();

        List<Integer> result = computePressure(stockHistory);
        List<Integer> expectedAnswer = new ArrayList<>();

        testAnswer("testNoDaysProvided", result, expectedAnswer);
    }

    public static void testLargeList() {
        List<Integer> stockHistory = Arrays.asList(100, 90, 80, 85, 90, 95, 100, 105, 110, 120, 140, 120, 100, 80);

        List<Integer> result = computePressure(stockHistory);
        List<Integer> expectedAnswer = Arrays.asList(1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 2, 6, 11);

        testAnswer("testLargeList", result, expectedAnswer);
    }

    public static void testRepeatingPrices() {
        List<Integer> stockHistory = Arrays.asList(10, 10, 10);

        List<Integer> result = computePressure(stockHistory);
        List<Integer> expectedAnswer = Arrays.asList(1, 1, 1);

        testAnswer("testRepeatingPrices", result, expectedAnswer);
    }

    public static void main(String[] args) {
        AlgoStreet.runUnitTests();
    }

}
