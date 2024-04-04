package main.aoc2023.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static java.nio.file.Path.of;

public class Task1 {

    // link: https://adventofcode.com/2023/day/1

    static Map<String, String> numbers = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    static Map<String, String> corrects = Map.of(
            "oneight","oneeight",
            "threeight","threeeight",
            "fiveight","fiveeight",
            "nineight","nineeight",
            "twone","twoone",
            "sevenine","sevennine",
            "eightwo","eighttwo");

//    public static void main(String[] args) {
//
//        // 1. read file by line
//        // 2. extract numbers from line and put them into a list
//        // 3. if list contains 2 numbers concatenate them and put them into a new list
//        // 4. if list contains more that 2 numbers, concatenate first and the last number and put them into a new list
//        // 5. if list contains only one number concatenate it with itself and put it into a new list
//        // 6. ignore empty lines or lines that do not contain numbers
//        // 7. sum all numbers in the list and print the result
//        partTwo();
//
//    }

    public Integer partOne() {
        try (Stream<String> lines = readByLine()) {

            return lines.map(Task1::replaceLetters)
                    .map(Task1::extractCalibrationValue)
                    .map(Integer::parseInt)
                    .reduce(Integer::sum)
                    .orElseThrow(() -> new RuntimeException("smth went wrong"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer partTwo() {
        // not covered by description that : "eighthree" is 83 and for "sevenine" is 79

        try (Stream<String> lines = readByLine()) {
            return lines.map( e -> wordToNumbers(e, corrects))
                    .map( e -> wordToNumbers(e, numbers))
                    .map(Task1::replaceLetters)
                    //.peek(System.out::println)
                    .map(Task1::extractCalibrationValue)
                    //.peek(System.out::println)
                    .map(Integer::parseInt)
                    .reduce(Integer::sum)
                    .orElseThrow(() -> new RuntimeException("smth went wrong"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static String replaceLetters(String string) {
        return string.replaceAll("\\D+", "");
    }

    private static String extractCalibrationValue(String string) {
        if (string.length() == 1) return string + string;
        if (string.length() == 2) return string;
        if (string.length() > 2)
            return (string.charAt(0) + "" + string.charAt(string.length() - 1)); // to return string
        throw new RuntimeException("Failed extract calibration value");
    }

    private static Stream<String> readByLine() throws IOException {
        return Files.lines(of("src/main/aoc2023/task1/input.txt"));
    }

    private static String wordToNumbers(String line, Map<String, String> collection) {
        AtomicReference<String> s = new AtomicReference<>(line);

        collection.forEach((key, value) -> s.set(replace(s.get(), key, value)));

        return s.get();
    }

    private static String replace(String line, String word, String number) {
        return line.replaceAll("(" + word + ")", number);
    }

    public String testWordToNumbers(String line) {
        return wordToNumbers(line, corrects);
    }

}
