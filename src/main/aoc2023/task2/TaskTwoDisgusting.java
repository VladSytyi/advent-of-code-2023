package main.aoc2023.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Path.of;

public class TaskTwoDisgusting {

    // This is Disgusting version, but gives the correct answer

    // link : https://adventofcode.com/2023/day/2

    // limit:  12 red cubes, 13 green cubes, and 14 blue cubes

    //Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green

    // var number = value.replaceAll("\\D", ""); // returns only numbers or replaces all non-digits with ""
//
//  var color = value.replaceAll("[0-9\\s]+", ""); // returns only letters or replaces all digits and spaces with ""


    public static void main(String[] args) throws IOException {
        try (var lines = readByLine()) {

            Map<Integer, String> games = lines.map(line -> line.split(":", 2)).collect(Collectors.toMap(a -> Integer.valueOf(extractDigit(a[0])), a -> a[1]));

            // convert into map with key as game number and value as list of games

            Map<Integer, List<String>> idsWithListOfGames = games.entrySet().stream().map(entry -> {
                var gameNumber = entry.getKey();
                var game = entry.getValue();
                var gameList = Arrays.asList(game.split(";"));
                return Map.entry(gameNumber, gameList);
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


            // convert into map with key as game number and value as list of true or false

            Map<Integer, List<Boolean>> gamesWithResults = idsWithListOfGames.entrySet().stream().map(entry -> {
                var gameNumber = entry.getKey();
                var gameList = entry.getValue();

                var gameResults = gameList.stream().map(v -> {
                    String[] strings = v.split(",");

                    return Arrays.stream(strings).map(step -> {

                        var number = Integer.valueOf(step.replaceAll("\\D", ""));
                        var color = step.replaceAll("[0-9\\s]+", "");

                        if (color.equals("red") && number > 12) return false;
                        if (color.equals("green") && number > 13) return false;
                        if (color.equals("blue") && number > 14) return false;

                        return true;

                    }).toList();
                }).toList();

                var flattenResults = gameResults.stream().map( r ->  r.stream().allMatch(e -> e)).toList();

                return Map.entry(gameNumber, flattenResults);
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


            Map<Integer, Boolean> finalCollection = gamesWithResults.entrySet().stream().map(entry -> {
                var game = entry.getKey();
                var toFlattenResult = entry.getValue();

                boolean result = toFlattenResult.stream().allMatch(e -> e);

                return Map.entry(game, result);

            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            Map<Integer, Boolean> collect = finalCollection.entrySet()
                    .stream()
                    .filter(Map.Entry::getValue)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            Integer collect1 = collect.keySet().stream().mapToInt(e -> e).sum();


            System.out.println(idsWithListOfGames);
            System.out.println(collect1);
        }


    }

    private static String extractDigit(String value) {
        return value.replaceAll("\\D", "");
    }

    private static String[] toKeyValue(String line) {
        return line.split(":", 2);
    }


    private static Stream<String> readByLine() throws IOException {
        return Files.lines(of("src/main/aoc2023/task2/input.txt"));
    }


    //Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green


}
