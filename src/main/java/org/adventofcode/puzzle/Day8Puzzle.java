package org.adventofcode.puzzle;

import org.adventofcode.models.Paths;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8Puzzle {
    public static long hauntedWasteLand(int part) throws IOException {
        return switch (part) {
            case 1 -> getShortestRoutePart1();
            case 2 -> getShortestRoutePart2();
            default -> 0;
        };
    }

    public static long getShortestRoutePart1() throws IOException {
        BufferedReader reader;
        HashMap<String, Paths> directionsMap = new HashMap<>();
        String instructions;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle8input"));
        String line = reader.readLine();
        instructions = line.trim();
        line = reader.readLine();
        line = reader.readLine();
        while(line != null){
            Pattern pattern = Pattern.compile("[a-zA-Z]{3}");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            String currentNode = matcher.group();
            matcher.find();
            String leftNode = matcher.group();
            matcher.find();
            String rightNode = matcher.group();
            directionsMap.put(currentNode,new Paths(leftNode,rightNode));
            line = reader.readLine();
        }
        String key = "AAA";
        boolean isDestination = true;
        long steps = 0;
        while(isDestination){
            char nextInstruction = instructions.charAt((int) (steps%(instructions.length())));
            key = directionsMap.get(key).getPath(nextInstruction);
            steps++;
            if(directionsMap.containsKey(key) && key.equals("ZZZ")){
                isDestination = false;
            }
        }
        return steps;
    }

    public static long getShortestRoutePart2() throws IOException {
        BufferedReader reader;
        HashMap<String, Paths> directionsMap = new HashMap<>();
        String instructions;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle8input"));
        String line = reader.readLine();
        instructions = line.trim();
        line = reader.readLine();
        line = reader.readLine();
        while(line != null){
            Pattern pattern = Pattern.compile("[a-zA-Z0-9]{3}");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            String currentNode = matcher.group();
            matcher.find();
            String leftNode = matcher.group();
            matcher.find();
            String rightNode = matcher.group();
            directionsMap.put(currentNode,new Paths(leftNode,rightNode));
            line = reader.readLine();
        }
        ArrayList<String> currentNodes = new ArrayList<>();
        for(String key: directionsMap.keySet()){
            if(key.endsWith("A")){
                currentNodes.add(key);
            }
        }
        ArrayList<Long> stepList = new ArrayList<>();
        for(String currentNode : currentNodes){
            long steps = 0;
            String key = currentNode;
            boolean isDestination = true;
            while(isDestination){
                char nextInstruction = instructions.charAt((int) (steps%(instructions.length())));
                key = directionsMap.get(key).getPath(nextInstruction);
                steps++;
                if(directionsMap.containsKey(key) && key.endsWith("Z")){
                    isDestination = false;
                }
            }
            stepList.add(steps);
        }
        return findLCM(stepList);
    }
    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    private static long findLCM(ArrayList<Long> longList) {
        long lcm = 1;
        for (long number : longList) {
            lcm = (lcm * number) / gcd(lcm, number);
        }
        return lcm;
    }
}
