package main.aoc2023.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

import static java.nio.file.Path.of;

public class Task3 {

    // link: https://adventofcode.com/2023/day/3
    public Integer partOne() {

        try(Stream<String> lines = readByLine()) {



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return 1;
    }

    private static Stream<String> readByLine() throws IOException {
        return Files.lines(of("src/main/aoc2023/task3/input.txt"));
    }
}
