package org.adventofcode.puzzle;

import org.adventofcode.models.NumberIndex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Puzzle {

    public static int gearRatio(int part) throws IOException {
        return switch (part) {
            case 1 -> getSumOfPartsPart1();
            case 2 -> getSumOfGearRatioPart2();
            default -> 0;
        };
    }

    public static int getSumOfGearRatioPart2() throws IOException {
        int sumOfParts = 0;
        HashMap<Integer, ArrayList<NumberIndex>> numberIndices = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> symbolIndices = new HashMap<>();
        ArrayList<Integer> symbolIndexArrayList = new ArrayList<>();
        ArrayList<NumberIndex> numberIndexArrayList = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle3input"));
        String line = reader.readLine();
        int index = 0;
        while (line != null) {
            numberIndexArrayList = new ArrayList<>();
            symbolIndexArrayList = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                NumberIndex numberIndex = new NumberIndex();
                numberIndex.setNumber(Integer.parseInt(matcher.group()));
                numberIndex.setStartIndex(matcher.start());
                numberIndex.setEndIndex(matcher.end()-1);
                numberIndexArrayList.add(numberIndex);
            }
            int j=0;
            for (char c : line.toCharArray()) {
                if (!Character.isLetterOrDigit(c) && c == '*') {
                    symbolIndexArrayList.add(j);
                }
                j++;
            }
            symbolIndices.put(index, symbolIndexArrayList);
            numberIndices.put(index, numberIndexArrayList);
            index++;
            line = reader.readLine();
        }
        for (int i = 0; i < index; i++) {
            for (Integer gear : symbolIndices.get(i)) {
                int gearVal = 1;
                int nearNums = 0;
                for (int j = Math.max(0, i - 1); j <= Math.min(i + 1, index - 1); j++) {
                    for (NumberIndex numberIndex : numberIndices.get(j)) {
                        if (isNeighbor(numberIndex, gear)) {
                            gearVal *= numberIndex.getNumber();
                            nearNums++;
                        }
                    }
                }
                if (nearNums == 2) {
                    sumOfParts += gearVal;
                }
            }
        }
        return sumOfParts;
    }

    public static int getSumOfPartsPart1() throws IOException {
        int sumOfParts = 0;
        HashMap<Integer, ArrayList<NumberIndex>> numberIndices = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> symbolIndices = new HashMap<>();
        ArrayList<Integer> symbolIndexArrayList = new ArrayList<>();
        ArrayList<NumberIndex> numberIndexArrayList = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle3input"));
        String line = reader.readLine();
        int index = 0;
        while (line != null) {
            numberIndexArrayList = new ArrayList<>();
            symbolIndexArrayList = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                NumberIndex numberIndex = new NumberIndex();
                numberIndex.setNumber(Integer.parseInt(matcher.group()));
                numberIndex.setStartIndex(matcher.start());
                numberIndex.setEndIndex(matcher.end()-1);
                numberIndexArrayList.add(numberIndex);
            }
            int j=0;
            for (char c : line.toCharArray()) {
                if (!Character.isLetterOrDigit(c) && c != '.') {
                    symbolIndexArrayList.add(j);
                }
                j++;
            }
            symbolIndices.put(index, symbolIndexArrayList);
            numberIndices.put(index, numberIndexArrayList);
            index++;
            line = reader.readLine();
        }
        for (int i = 0; i < index; i++) {
            for (NumberIndex numberIndex : numberIndices.get(i)) {
                for (int j = Math.max(0, i - 1); j <= Math.min(i + 1, index - 1); j++) {
                    for (Integer symbol : symbolIndices.get(j)) {
                        if (isNeighbor(numberIndex, symbol)) {
                            sumOfParts += numberIndex.getNumber();
                            numberIndex.setIsPartNumber(true);
                            break;
                        }
                    }
                    if(numberIndex.isPartNumber()){
                        break;
                    }
                }
            }
        }
        return sumOfParts;
    }

    private static boolean isNeighbor(NumberIndex numberIndex, int symbol) {
        return symbol >= numberIndex.getStartIndex() - 1 && symbol <= numberIndex.getEndIndex() + 1;
    }
}
