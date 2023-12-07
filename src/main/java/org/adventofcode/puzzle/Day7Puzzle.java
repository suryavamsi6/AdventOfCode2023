package org.adventofcode.puzzle;

import org.adventofcode.models.Hand;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day7Puzzle {
    public static int camelCards(int part) throws IOException {
        return switch (part) {
            case 1 -> getTotalWinningsPart1();
            case 2 -> getTotalWinningsPart2();
            default -> 0;
        };
    }

    public static int getTotalWinningsPart1() throws IOException {
        BufferedReader reader;
        HashMap<String, Integer> handBetMap = new HashMap<>();
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle7input"));
        String line = reader.readLine();
        while (line != null) {
            String[] splitLine = line.split(" ");
            handBetMap.put(splitLine[0], Integer.valueOf(splitLine[1]));
            line = reader.readLine();
        }
        ArrayList<Character> cardRankLike = new ArrayList<>(List.of('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'));
        Collections.reverse(cardRankLike);
        TreeMap<Hand, Integer> handWin = new TreeMap<>();

        for (String key : handBetMap.keySet()) {
            int[] charOccurrences = new int[13];
            Integer bet = handBetMap.get(key);
            ArrayList<Integer> card = new ArrayList<>();
            for (char c : key.toCharArray()) {
                card.add(cardRankLike.indexOf(c));
                charOccurrences[cardRankLike.indexOf(c)]++;
            }
            Arrays.sort(charOccurrences);
            int strength = 2 * charOccurrences[charOccurrences.length - 1];
            if (charOccurrences[charOccurrences.length - 2] == 2) {
                strength += 1; //for full house and two pair
            }
            handWin.put(new Hand(card,strength), bet);
        }
        int rank = 1;
        int totalWinning = 0;
        for (Hand key : handWin.keySet()) {
            totalWinning += (handWin.get(key) * rank);
            rank++;
        }


        return totalWinning;
    }


    public static int getTotalWinningsPart2() throws IOException {
        BufferedReader reader;
        HashMap<String, Integer> handBetMap = new HashMap<>();
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle7input"));
        String line = reader.readLine();
        while (line != null) {
            String[] splitLine = line.split(" ");
            handBetMap.put(splitLine[0], Integer.valueOf(splitLine[1]));
            line = reader.readLine();
        }
        ArrayList<Character> cardRankLike = new ArrayList<>(List.of('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J'));
        Collections.reverse(cardRankLike);
        TreeMap<Hand, Integer> handWin = new TreeMap<>();

        for (String key : handBetMap.keySet()) {
            int[] charOccurrences = new int[13];
            int jokers = 0;
            Integer bet = handBetMap.get(key);
            ArrayList<Integer> card = new ArrayList<>();
            for (char c : key.toCharArray()) {
                if(c == 'J'){
                    jokers++;
                }
                else {
                    charOccurrences[cardRankLike.indexOf(c)]++;
                }
                card.add(cardRankLike.indexOf(c));
            }
            Arrays.sort(charOccurrences);
            charOccurrences[charOccurrences.length -1 ] += jokers;
            int strength = 2 * charOccurrences[charOccurrences.length - 1];
            if (charOccurrences[charOccurrences.length - 2] == 2) {
                strength += 1; //for full house and two pair
            }
            handWin.put(new Hand(card,strength), bet);
        }
        int rank = 1;
        int totalWinning = 0;
        for (Hand key : handWin.keySet()) {
            totalWinning += (handWin.get(key) * rank);
            rank++;
        }


        return totalWinning;
    }


}
