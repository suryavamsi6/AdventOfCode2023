package org.adventofcode.puzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4Puzzle {
    public static int scratchCards(int part) throws IOException {
        return switch (part) {
            case 1 -> getSumOfWinningCardsPart1();
            case 2 -> getSumOfScratchCardsPart2();
            default -> 0;
        };
    }

    public static int getSumOfWinningCardsPart1() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle4input"));
        String line = reader.readLine();
        ArrayList<Integer> winningNumbers = new ArrayList<>();
        ArrayList<Integer> myNumbers = new ArrayList<>();
        int sumOfWinningNumbers = 0;
        int numberOfWins;
        while(line != null){
            numberOfWins = -1;
            winningNumbers = new ArrayList<>();
            myNumbers = new ArrayList<>();

            String[] subString = line.split("\\|");
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(subString[0]);
            while (matcher.find()) {
                winningNumbers.add(Integer.valueOf(matcher.group()));
            }
            pattern = Pattern.compile("\\d+");
            matcher = pattern.matcher(subString[1]);
            while (matcher.find()) {
                myNumbers.add(Integer.valueOf(matcher.group()));
            }
            winningNumbers.remove(0);
            for(Integer integer: myNumbers){
                if(winningNumbers.contains(integer)){
                    numberOfWins++;
                }
            }
            sumOfWinningNumbers += (int) Math.pow(2,numberOfWins);
            line = reader.readLine();
        }
        return sumOfWinningNumbers;

    }

    public static int getSumOfScratchCardsPart2() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle4input"));
        String line = reader.readLine();
        ArrayList<Integer> winningNumbers = new ArrayList<>();
        ArrayList<Integer> myNumbers = new ArrayList<>();
        ArrayList<String> allCards = new ArrayList<>();
        HashMap<Integer,Integer> scratchCards = new HashMap<>();

        int sumOfAllCards = 0;
        int numberOfWins;
        int index = 0;

        while(line != null){
            allCards.add(line);
            scratchCards.put(index,1);
            index++;
            line = reader.readLine();
        }

        for(String card: allCards){
            numberOfWins = 0;
            winningNumbers = new ArrayList<>();
            myNumbers = new ArrayList<>();

            String[] subString = card.split("\\|");
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(subString[0]);
            while (matcher.find()) {
                winningNumbers.add(Integer.valueOf(matcher.group()));
            }
            pattern = Pattern.compile("\\d+");
            matcher = pattern.matcher(subString[1]);
            while (matcher.find()) {
                myNumbers.add(Integer.valueOf(matcher.group()));
            }
            winningNumbers.remove(0);
            for(Integer integer: myNumbers){
                if(winningNumbers.contains(integer)){
                    numberOfWins++;
                }
            }
            int copies = scratchCards.get(allCards.indexOf(card));
            for(int i = allCards.indexOf(card)+1;i<=numberOfWins+allCards.indexOf(card);i++){
                scratchCards.computeIfPresent(i,(k,v) -> v+copies);
            }
        }

        for(Integer key: scratchCards.keySet()){
            sumOfAllCards += scratchCards.get(key);
        }

        return sumOfAllCards;

    }
}
