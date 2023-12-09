package org.adventofcode;


import org.adventofcode.puzzle.*;

import java.io.IOException;
import java.math.BigInteger;


public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            runAllPuzzles();
        } else {
            int puzzleNumber = Integer.parseInt(args[0]);
            int result = 0;
            long longResult= 0;
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
                case 6:
                    bigIntegerResult = Day6Puzzle.waitForIt(part);
                    break;
                case 7:
                    result = Day7Puzzle.camelCards(part);
                    break;
                case 8:
                    longResult = Day8Puzzle.hauntedWasteLand(part);
                    break;
                case 9:
                    longResult = Day9Puzzle.mirageMaintenance(part);
                    break;
                default:
                    System.out.println("Enter a Puzzle Number!!");

            }
            System.out.println(longResult);
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
        System.out.println("Day 5:");
        System.out.println("    Part 1:" + Day5Puzzle.getLowestLocationPart1());
        System.out.println("    Part 2:" + Day5Puzzle.getLowestLocationForRangePart2());
        System.out.println("Day 6:");
        System.out.println("    Part 1:" + Day6Puzzle.getMarginOfErrorPart1());
        System.out.println("    Part 2:" + Day6Puzzle.getMarginOfErrorPart2());
        System.out.println("Day 7:");
        System.out.println("    Part 1:" + Day7Puzzle.getTotalWinningsPart1());
        System.out.println("    Part 2:" + Day7Puzzle.getTotalWinningsPart2());
        System.out.println("Day 8:");
        System.out.println("    Part 1:" + Day8Puzzle.getShortestRoutePart1());
        System.out.println("    Part 2:" + Day8Puzzle.getShortestRoutePart2());
        System.out.println("Day 9:");
        System.out.println("    Part 1:" + Day9Puzzle.getSumOfExtrapolatesPart1());
        System.out.println("    Part 2:" + Day9Puzzle.getSumOfExtrapolatesPart2());

    }


}