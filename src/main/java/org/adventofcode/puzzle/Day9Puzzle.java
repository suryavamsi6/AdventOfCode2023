package org.adventofcode.puzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day9Puzzle {
    public static long mirageMaintenance(int part) throws IOException {
        return switch (part) {
            case 1 -> getSumOfExtrapolatesPart1();
            case 2 -> getSumOfExtrapolatesPart2();
            default -> 0;
        };
    }

    public static long getSumOfExtrapolatesPart1() throws IOException{
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle9input"));
        String line = reader.readLine();
        long sumOfExtrapolates = 0;
        while(line != null){
            ArrayList<Long> numberList = new ArrayList<>(Arrays.stream(line.split(" ")).map(Long::valueOf).toList());
            sumOfExtrapolates += numberList.getLast() + extrapolateRow(numberList);
            line = reader.readLine();
        }
        return sumOfExtrapolates;
    }

    private static long extrapolateRow(ArrayList<Long> numberList) {
        ArrayList<Long> newList = new ArrayList<>();
        for(int i = 0;i<numberList.size()-1;i++){
            newList.add(numberList.get(i+1)-numberList.get(i));
        }
        if(newList.stream().reduce(0L, Long::sum)==0){
            return 0;
        }
        return newList.getLast() + extrapolateRow(newList);
    }

    public static long getSumOfExtrapolatesPart2() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle9input"));
        String line = reader.readLine();
        long sumOfExtrapolates = 0;
        while(line != null){
            ArrayList<Long> numberList = new ArrayList<>(Arrays.stream(line.split(" ")).map(Long::valueOf).toList());
            sumOfExtrapolates += numberList.getFirst() - extrapolateRowBackwards(numberList);
            line = reader.readLine();
        }
        return sumOfExtrapolates;
    }

    private static long extrapolateRowBackwards(ArrayList<Long> numberList) {
        ArrayList<Long> newList = new ArrayList<>();
        for(int i = 0;i<numberList.size()-1;i++){
            newList.add(numberList.get(i+1)-numberList.get(i));
        }
        if(newList.stream().reduce(0L, Long::sum)==0){
            return 0;
        }
        return newList.getFirst() - extrapolateRowBackwards(newList);
    }

}
