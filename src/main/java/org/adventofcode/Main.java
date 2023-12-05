package org.adventofcode;


import org.adventofcode.puzzle.*;

import java.io.IOException;
import java.math.BigInteger;


public class Main {

    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            runAllPuzzles();
        }
        else {
            int puzzleNumber = Integer.parseInt(args[0]);
            int result = 0;
            BigInteger bigIntegerResult = null;
            int part = Integer.parseInt(args[1]);
            switch (puzzleNumber) {
                case 1:
                    result = Day1Puzzle.sumOfCalibrationValues(part);
                    break;
                case 2:
                    result = Day2Puzzle.getSumOfPossibleGames(part);
                    break;
                case 3:
                    result = Day3Puzzle.gearRatio(part);
                    break;
                case 4:
                    result = Day4Puzzle.scratchCards(part);
                    break;
                case 5:
                    bigIntegerResult = Day5Puzzle.gardeningSeed(part);
                    break;
                default:
                    System.out.println("Enter a Puzzle Number!!");

            }
            System.out.println(bigIntegerResult);
        }
    }

    private static void runAllPuzzles() throws IOException {
        System.out.println("Day 1:");
        System.out.println("    Part 1:" + Day1Puzzle.sumOfCalibrationValuesPart1());
        System.out.println("    Part 2:" + Day1Puzzle.sumOfCalibrationValuesPart2());
        System.out.println("Day 2:");
        System.out.println("    Part 1:" + Day2Puzzle.getSumOfPossibleGamesPart1());
        System.out.println("    Part 2:" + Day2Puzzle.getSumOfPossibleGamesPart2());
        System.out.println("Day 3:");
        System.out.println("    Part 1:" + Day3Puzzle.getSumOfPartsPart1());
        System.out.println("    Part 2:" + Day3Puzzle.getSumOfGearRatioPart2());
        System.out.println("Day 4:");
        System.out.println("    Part 1:" + Day4Puzzle.getSumOfWinningCardsPart1());
        System.out.println("    Part 2:" + Day4Puzzle.getSumOfScratchCardsPart2());
        System.out.println("Day 4:");
        System.out.println("    Part 1:" + Day5Puzzle.getLowestLocationPart1());
        System.out.println("    Part 2:" + Day5Puzzle.getLowestLocationForRangePart2());
    }


}