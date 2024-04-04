package main.aoc2023.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.nio.file.Path.of;

public class Task2 {

    // link : https://adventofcode.com/2023/day/2

    // 12 red cubes, 13 green cubes, and 14 blue cubes
    // Game 1: 7 blue, 6 green, 3 red; 3 red, 5 green, 1 blue; 1 red, 5 green, 8 blue; 3 red, 1 green, 5 blue
    // Sum of games( game 1 , game 3 , game 4 = 8)  which is possible
    // Game 1: 23 blue, 17 green, 10 red

    // split by semi

    /*
    The correct regexp helped me to solve those task  : It's a very tricky regexp
    Matching is divided into 2 parts:
     First part:  Game (\d+)   -- to extract game Id
     Second match: (\d+) (red|blue|green) -- all colors

     Second match could return 1 to n matched results, so I've collected all them in one stream

     at the end used function .allMatch() to validate all colors

     */

    public Integer partOne() {
        Pattern pattern = Pattern.compile("Game (\\d+)|(\\d+) (red|blue|green)");

        try(Stream<String> lines = readByLine()) {
            return lines.mapToInt(line -> {
                Matcher matcher = pattern.matcher(line);
                matcher.find();
                int gameId = Integer.parseInt(matcher.group(1));// game id

                boolean allMatch = matcher.results()
                        .allMatch(matchResult -> {
                            int number = Integer.parseInt(matchResult.group(2));
                            String color = matchResult.group(3);

                            if (color.equals("blue") && number > 14) return false;
                            if (color.equals("red") && number > 12) return false;
                            if (color.equals("green") && number > 13) return false;

                            return true;
                        });

                if (allMatch) return gameId;
                return 0; // default sum = 0 , means that game contains invalid
            }).sum();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*

    // link : https://adventofcode.com/2023/day/2  see --- Part Two --- section

    The regexp is used from above, the only change is an algorithm

    We need to find the MAX value per each color that the game would be possible

    If any color is not present int the game, those means that MAX value is 0 , game is not possible

    so we need to remove the game from calculation

     */

    public Integer partTwo() {
        Pattern pattern = Pattern.compile("Game (\\d+)|(\\d+) (red|blue|green)");

        try(Stream<String> lines = readByLine()) {
            return lines.map(line -> {
                Matcher matcher = pattern.matcher(line);
                matcher.find();
                int gameId = Integer.parseInt(matcher.group(1));// game id

                // find max cubes per each color per game

                AtomicInteger maxGreen = new AtomicInteger(0);
                AtomicInteger maxBlue = new AtomicInteger(0);
                AtomicInteger maxRed = new AtomicInteger(0);

                matcher.results()
                        .forEach(matchResult -> {
                            int number = Integer.parseInt(matchResult.group(2));
                            String color = matchResult.group(3);

                            if (color.equals("blue") && number > maxBlue.get()) {
                               maxBlue.set(number);
                            }

                            if (color.equals("red") && number > maxRed.get()) {
                                maxRed.set(number);
                            }

                            if (color.equals("green") && number > maxGreen.get()) {
                                maxGreen.set(number);
                            }

                        });

                return new Game(gameId, maxBlue.get(), maxRed.get(), maxGreen.get());

            })
                    .filter(game -> game.maxBlue() != 0)  // filter all games where blue color is not in the bag
                    .filter(game -> game.maxGreen() != 0) // filter all games where green color is not in the bag
                    .filter(game -> game.maxRed() != 0) // filter all games where red color is not in the bag
                    .mapToInt( g -> g.maxRed() * g.maxBlue() * g.maxGreen() )
                    .sum();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<String> readByLine() throws IOException {
        return Files.lines(of("src/main/aoc2023/task2/input.txt"));
    }

    record Game(Integer game, Integer maxBlue, Integer maxRed, Integer maxGreen) {}
}
