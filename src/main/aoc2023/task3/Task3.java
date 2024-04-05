package main.aoc2023.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.nio.file.Path.of;

public class Task3 {

    private static final Pattern symbolPattern = Pattern.compile("[^a-zA-Z0-9.]");
    private static final Pattern generalPattern = Pattern.compile("(\\d+)|([^a-zA-Z0-9.])");
    private static final Pattern digitPattern = Pattern.compile("(\\d+)");

    // link: https://adventofcode.com/2023/day/3

    // Questions:
    // 1. How to determine all symbols ? -- use regexp
    // 2. Sum all digits that is adjacent to the number
    //  how to calculate all adjacent numbers of symbol
    /*

        . . . |      . 2 3 .
        . * .        . * . .
        . . .        . . . 3

           1. find position of star
           2. put the first line into the stack
           we iterate over stack
     */
    public Integer partOne() {

        try(Stream<String> lines = readByLine()) {



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return 1;
    }

    public static void main(String[] args) {

        test();

    }

    // class to store the line number, and list of ranges with digits and symbols

    private static Integer test() {
        AtomicInteger counter = new AtomicInteger(0);
        List<Detail> list = input.lines()
                .map(line -> {
                    Matcher symbolMatcher = symbolPattern.matcher(line);
                    Matcher digitMatcher = digitPattern.matcher(line);

                    // composite numbers
                    List<Point> symbols = symbolMatcher.results()
                            .map(matchResult -> new Point(matchResult.start(), matchResult.end()))
                            .toList();

                    List<Point> digits = digitMatcher.results()
                            .map(matchResult -> new Point(matchResult.start(), matchResult.end()))
                            .toList();

                    return new Detail(counter.getAndIncrement(), digits, symbols);


                }).toList();

        System.out.println(list);

        return 1;

    }

    record Detail(Integer line, List<Point> numbers, List<Point> symbols ) {}

    record Point(Integer x, Integer y) {
        public Set<Point> neighbors() {
            return Set.of(
                    new Point(x - 1, y - 1),
                    new Point(x, y - 1),
                    new Point(x + 1, y - 1),
                    new Point(x - 1, y),
                    new Point(x + 1, y),
                    new Point(x - 1, y + 1),
                    new Point(x, y + 1),
                    new Point(x + 1, y + 1));
        }
    }


    static String input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
            """;

    private static Stream<String> readByLine() throws IOException {
        return Files.lines(of("src/main/aoc2023/task3/input.txt"));
    }
}
