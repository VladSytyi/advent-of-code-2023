package main.aoc2023;

import main.aoc2023.task1.Task1;
import main.aoc2023.task2.Task2;

public class Main {
    public static void main(String[] args) {

        Task1 task1 = new Task1();

//        Integer partOne = task1.partOne();
//
//        System.out.println("Answer to the Task 1 - part 1 is " + partOne);
//        System.out.println("Answer to the Task 1 - part 2 is " + partOne);

        Task2 task2 = new Task2();
        Integer task2PartOneFinal = task2.partOne();
        Integer task2PartTwo = task2.partTwo();


        System.out.println("Answer to the Task 2 - part 1 is " + task2PartOneFinal);
        System.out.println("Answer to the Task 2 - part 2 is " + task2PartTwo);


    }
}