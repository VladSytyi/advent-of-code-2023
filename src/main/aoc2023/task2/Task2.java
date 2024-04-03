package main.aoc2023.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

import static java.nio.file.Path.of;

public class Task2 {


    public Integer partOne() {


        return 1;
    }

    private static Stream<String> readByLine() throws IOException {
        return Files.lines(of("src/main/aoc2023/task2/input.txt"));
    }
}
