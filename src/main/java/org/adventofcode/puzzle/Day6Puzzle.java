package org.adventofcode.puzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6Puzzle {

    public static BigInteger waitForIt(int part) throws IOException {
        return switch (part) {
            case 1 -> getMarginOfErrorPart1();
            case 2 -> getMarginOfErrorPart2();
            default -> BigInteger.ZERO;
        };
    }

    public static BigInteger getMarginOfErrorPart1() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle6input"));
        String line = reader.readLine();
        Pattern pattern = Pattern.compile("\\b\\d+\\b");
        Matcher matcher = pattern.matcher(line);
        ArrayList<Integer> timeArray = new ArrayList<>();
        ArrayList<Integer> recordArray = new ArrayList<>();
        while (matcher.find()) {
            timeArray.add(Integer.valueOf(matcher.group()));
        }
        line = reader.readLine();
        matcher = pattern.matcher(line);
        while (matcher.find()) {
            recordArray.add(Integer.valueOf(matcher.group()));
        }
        int marginOfError = 1;
        int count;
        for (int i = 0; i < timeArray.size(); i++) {
            int time = timeArray.get(i);
            int record = recordArray.get(i);
            count = 0;
            for (int j = 0; j <= time; j++) {
                if (j * (time - j) > record) {
                    count++;
                }
            }
            marginOfError *= count;
        }
        return BigInteger.valueOf(marginOfError);
    }

    public static BigInteger getMarginOfErrorPart2() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle6input"));
        String line = reader.readLine();
        Pattern pattern = Pattern.compile("\\b\\d+\\b");
        Matcher matcher = pattern.matcher(line);
        BigInteger timeInt = BigInteger.ONE;
        BigInteger recordInt = BigInteger.ONE;
        String number = "";
        while (matcher.find()) {
            number = number.concat(matcher.group());
        }
        timeInt = new BigInteger(number);
        line = reader.readLine();
        matcher = pattern.matcher(line);
        number = "";
        while (matcher.find()) {
            number = number.concat(matcher.group());
        }
        recordInt = new BigInteger(number);
        BigInteger marginOfError = BigInteger.ZERO;


        for (BigInteger j = BigInteger.valueOf(0); j.compareTo(timeInt)<=0; j = j.add(BigInteger.ONE)) {
            if (j.multiply(timeInt.subtract(j)).compareTo(recordInt)>0) {
                marginOfError = marginOfError.add(BigInteger.ONE);
            }
        }

        return marginOfError;
    }
}
