package main.aoc2023;

import main.aoc2023.task1.Task1;
import main.aoc2023.task2.Task2Old;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        Task1 task1 = new Task1();

        Integer partOne = task1.partOne();

        System.out.println("Answer to the Task 1 - part 1 is " + partOne);
        System.out.println("Answer to the Task 1 - part 2 is " + partOne);

        Task2Old task2 = new Task2Old();



    }
}