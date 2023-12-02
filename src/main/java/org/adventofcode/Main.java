package org.adventofcode;


import org.adventofcode.puzzle.Day1Puzzle;
import org.adventofcode.puzzle.Day2Puzzle;

import java.io.IOException;


public class Main {



    public static void main(String[] args) throws IOException {
        int puzzleNumber = Integer.parseInt(args[0]);
        int result = 0;
        int part = Integer.parseInt(args[1]);
        switch (puzzleNumber) {
            case 1:
                result = Day1Puzzle.sumOfCalibrationValues(part);
                break;
            case 2:
                result = Day2Puzzle.getSumOfPossibleGames(part);
                break;
            default:
                System.out.println("Enter a Puzzle Number!!");
                
        }
        System.out.println(result);
    }


}