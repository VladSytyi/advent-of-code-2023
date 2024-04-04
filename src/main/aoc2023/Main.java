package main.aoc2023;

import main.aoc2023.task1.Task1;
import main.aoc2023.task2.Task2;
import main.aoc2023.task3.Task3;

public class Main {
    public static void main(String[] args) {

        Task1 task1 = new Task1();

        Integer task1PartOne = task1.partOne();
        Integer task1Part2 = task1.partTwo();

        System.out.println("Answer to the Task 1 p.1 is " + task1PartOne);
        System.out.println("Answer to the Task 1 p.2 is " + task1Part2);

        Task2 task2 = new Task2();
        Integer task2PartOneFinal = task2.partOne();
        Integer task2PartTwo = task2.partTwo();


        System.out.println("Answer to the Task 2 p.1 is " + task2PartOneFinal);
        System.out.println("Answer to the Task 2 p.2 is " + task2PartTwo);


        Task3 task3 = new Task3();
        Integer task3partOne = task3.partOne();

        System.out.println("Answer to the Task 3 p.1 is " + task3partOne);


    }
}