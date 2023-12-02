package org.adventofcode.models;

import java.util.HashMap;
import java.util.Map;

public class IndexWordMap implements Comparable<IndexWordMap> {
    int index;
    String matchedWord;

    private static final Map<String, Integer> wordToNumberMap = new HashMap<>();

    static {
        // Rule-based mapping of words to numbers
        wordToNumberMap.put("one", 1);
        wordToNumberMap.put("two", 2);
        wordToNumberMap.put("three", 3);
        wordToNumberMap.put("four", 4);
        wordToNumberMap.put("five", 5);
        wordToNumberMap.put("six", 6);
        wordToNumberMap.put("seven", 7);
        wordToNumberMap.put("eight", 8);
        wordToNumberMap.put("nine", 9);
        // Add more words and their numeric equivalents based on your rules
    }
    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "IndexWordMap{" +
                "index=" + index +
                ", matchedWord='" + matchedWord + '\'' +
                '}';
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getMatchedWord() {
        return matchedWord;
    }

    public void setMatchedWord(String matchedWord) {
        this.matchedWord = matchedWord;
    }


    @Override
    public int compareTo(IndexWordMap o) {
        return Integer.compare(this.index, o.getIndex());

    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?"); // Regular expression to check for a number
    }

    public static String convertWordToNumber(String word) {
        if (isNumeric(word)) {
            return word; // Convert string number to integer
        } else {
            return wordToNumberMap.getOrDefault(word.toLowerCase(), -1).toString();
        }
    }
}
