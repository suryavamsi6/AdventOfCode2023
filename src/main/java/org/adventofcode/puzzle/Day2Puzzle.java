package org.adventofcode.puzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2Puzzle {

    public static int getSumOfPossibleGames(int part) throws IOException {
        return switch (part) {
            case 1 -> getSumOfPossibleGamesPart1();
            case 2 -> getSumOfPossibleGamesPart2();
            default -> 0;
        };
    }

    private static int getSumOfPossibleGamesPart1() throws IOException {
        boolean validGame;
        int sumOfValidGameId = 0;
        int maxRed = 12;
        int maxBlue = 14;
        int maxGreen = 13;
        ArrayList<Integer> countList = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle2input.txt"));
        String line = reader.readLine();
        while(line != null){
            validGame = true;
            countList = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()){
                countList.add(Integer.valueOf(matcher.group()));
            }
            pattern = Pattern.compile("(red|blue|green)", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(line);
            int k = 1;
            while(matcher.find()){
                switch (matcher.group()){
                    case "red":
                        if(countList.get(k)>maxRed)
                            validGame = false;
                        break;
                    case "blue":
                        if(countList.get(k)>maxBlue)
                            validGame = false;
                        break;
                    case "green":
                        if(countList.get(k)>maxGreen)
                            validGame = false;
                        break;
                    default:
                        break;
                }
                k++;
            }

            if(validGame){
                int gameId = countList.get(0);
                sumOfValidGameId += gameId;
            }
            line = reader.readLine();
        }
        return sumOfValidGameId;
    }

    private static int getSumOfPossibleGamesPart2() throws IOException {
        boolean validGame;
        int sumOfPowerOfSet = 0;
        int maxRed ;
        int maxBlue ;
        int maxGreen ;
        ArrayList<Integer> countList = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle2input.txt"));
        String line = reader.readLine();
        while(line != null){
            maxBlue = 0;
            maxRed = 0;
            maxGreen = 0;

            countList = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()){
                countList.add(Integer.valueOf(matcher.group()));
            }
            pattern = Pattern.compile("(red|blue|green)", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(line);
            int k = 1;
            while(matcher.find()){
                switch (matcher.group()){
                    case "red":
                        if(countList.get(k)>maxRed)
                            maxRed = countList.get(k);
                        break;
                    case "blue":
                        if(countList.get(k)>maxBlue)
                            maxBlue = countList.get(k);
                        break;
                    case "green":
                        if(countList.get(k)>maxGreen)
                            maxGreen = countList.get(k);
                        break;
                    default:
                        break;
                }
                k++;
            }
            sumOfPowerOfSet += maxRed*maxBlue*maxGreen;
            line = reader.readLine();
        }
        return sumOfPowerOfSet;
    }
}
