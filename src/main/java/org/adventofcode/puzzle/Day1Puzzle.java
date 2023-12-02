package org.adventofcode.puzzle;

import org.adventofcode.models.IndexWordMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Day1Puzzle {

    static

    public int sumOfCalibrationValues(int part) throws IOException {
        return switch (part) {
            case 1 -> sumOfCalibrationValuesPart1();
            case 2 -> sumOfCalibrationValuesPart2();
            default -> 0;
        };
    }

    public static int sumOfCalibrationValuesPart1() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle1input.txt"));
        String line = reader.readLine();
        int sumOfCalibration = 0;

        while(line != null){
            String numberOnly= line.replaceAll("[^0-9]", "");
            String number = String.valueOf(numberOnly.charAt(0)) + String.valueOf(numberOnly.charAt(numberOnly.length()-1));
            System.out.println(number + " "+ numberOnly);
            sumOfCalibration += Integer.parseInt(number);
            line = reader.readLine();
        }
        return sumOfCalibration;

    }

    public static int sumOfCalibrationValuesPart2() throws IOException {
        ArrayList<IndexWordMap> indexes;
        IndexWordMap indexWordMap;
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle1input.txt"));
        String line = reader.readLine();
        int sumOfCalibration = 0;

        while(line != null){
            indexes = new ArrayList<IndexWordMap>();
            Pattern pattern = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()){
                indexWordMap = new IndexWordMap();
                indexWordMap.setIndex(matcher.start());
                indexWordMap.setMatchedWord(matcher.group());
                indexes.add(indexWordMap);
                matcher.region(matcher.start() + 1, line.length());
            }


            pattern = Pattern.compile("0|1|2|3|4|5|6|7|8|9", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(line);
            while(matcher.find()){
                indexWordMap = new IndexWordMap();
                indexWordMap.setIndex(matcher.start());
                indexWordMap.setMatchedWord(matcher.group());
                indexes.add(indexWordMap);
            }
            Collections.sort(indexes);
            int size = indexes.size();
            String number = null;
            if(size>1){
                number = IndexWordMap.convertWordToNumber(indexes.get(0).getMatchedWord()) + IndexWordMap.convertWordToNumber(indexes.get(size-1).getMatchedWord());
            }
            else {
                number = IndexWordMap.convertWordToNumber(indexes.get(0).getMatchedWord()) + IndexWordMap.convertWordToNumber(indexes.get(0).getMatchedWord());
            }
            System.out.println(line + "->" + number);
            sumOfCalibration += Integer.parseInt(number);
            line = reader.readLine();
        }
        return sumOfCalibration;
    }
}
