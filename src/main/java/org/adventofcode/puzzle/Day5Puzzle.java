package org.adventofcode.puzzle;

import org.adventofcode.models.DestinationRangeMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5Puzzle {

    public static BigInteger gardeningSeed(int part) throws IOException {
        return switch (part) {
            case 1 -> getLowestLocationPart1();
            case 2 -> getLowestLocationForRangePart2();
            default -> BigInteger.ZERO;
        };
    }

    public static BigInteger getLowestLocationPart1() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle5input"));
        String line = reader.readLine();
        HashMap<BigInteger,BigInteger> seedList = new HashMap<>();
        HashMap<BigInteger, DestinationRangeMap> seedToSoilMap = new HashMap<>();
        HashMap<BigInteger,DestinationRangeMap> soilToFertMap = new HashMap<>();
        HashMap<BigInteger,DestinationRangeMap> fertToWaterMap = new HashMap<>();
        HashMap<BigInteger,DestinationRangeMap> waterToLightMap = new HashMap<>();
        HashMap<BigInteger,DestinationRangeMap> lightToTempMap = new HashMap<>();
        HashMap<BigInteger,DestinationRangeMap> tempToHumidityMap = new HashMap<>();
        HashMap<BigInteger,DestinationRangeMap> humidityToLocMap = new HashMap<>();
        while(!line.isEmpty())
        {
            Pattern pattern = Pattern.compile("\\b\\d+\\b");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                seedList.put(BigInteger.valueOf(Long.parseLong(matcher.group())),BigInteger.valueOf(Long.parseLong(matcher.group())));
            }
            line = reader.readLine();
        }
        getMapping(reader, seedToSoilMap);
        getMapping(reader, soilToFertMap);
        getMapping(reader, fertToWaterMap);
        getMapping(reader, waterToLightMap);
        getMapping(reader, lightToTempMap);
        getMapping(reader, tempToHumidityMap);
        getMapping(reader, humidityToLocMap);

        for(BigInteger key : seedList.keySet()){
            BigInteger value = seedList.get(key);
            value = getValue(value,seedToSoilMap);
            value = getValue(value,soilToFertMap);
            value = getValue(value,fertToWaterMap);
            value = getValue(value,waterToLightMap);
            value = getValue(value,lightToTempMap);
            value = getValue(value,tempToHumidityMap);
            value = getValue(value,humidityToLocMap);
            seedList.put(key,value);
        }

        BigInteger lowest = null;

        for (BigInteger value : seedList.values()) {
            if (lowest == null || value.compareTo(lowest) < 0) {
                lowest = value;
            }
        }


        return lowest;
    }

    public static BigInteger getLowestLocationForRangePart2() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle5input"));
        String line = reader.readLine();
        HashMap<BigInteger,DestinationRangeMap> seedMap = new HashMap<>();
        HashMap<BigInteger,DestinationRangeMap> seedToSoilMap = new HashMap<>();
        HashMap<BigInteger,DestinationRangeMap> soilToFertMap = new HashMap<>();
        HashMap<BigInteger,DestinationRangeMap> fertToWaterMap = new HashMap<>();
        HashMap<BigInteger,DestinationRangeMap> waterToLightMap = new HashMap<>();
        HashMap<BigInteger,DestinationRangeMap> lightToTempMap = new HashMap<>();
        HashMap<BigInteger,DestinationRangeMap> tempToHumidityMap = new HashMap<>();
        HashMap<BigInteger,DestinationRangeMap> humidityToLocMap = new HashMap<>();
        while(!line.isEmpty())
        {
            Pattern pattern = Pattern.compile("\\b\\d+\\b");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                BigInteger rangeStart = new BigInteger(matcher.group());
                matcher.find();
                BigInteger range = new BigInteger(matcher.group());
                seedMap.put(rangeStart,new DestinationRangeMap(rangeStart.add(range.subtract(BigInteger.ONE)),range));
            }
            line = reader.readLine();
        }
        getMappingPart2(reader, seedToSoilMap);
        getMappingPart2(reader, soilToFertMap);
        getMappingPart2(reader, fertToWaterMap);
        getMappingPart2(reader, waterToLightMap);
        getMappingPart2(reader, lightToTempMap);
        getMappingPart2(reader, tempToHumidityMap);
        getMappingPart2(reader, humidityToLocMap);
        BigInteger index = BigInteger.ZERO;
        while(index.compareTo(new BigInteger("100000000"))!=0){
            BigInteger indexValue;
            indexValue = getValue(index,humidityToLocMap);
            indexValue = getValue(indexValue,tempToHumidityMap);
            indexValue = getValue(indexValue,lightToTempMap);
            indexValue = getValue(indexValue,waterToLightMap);
            indexValue = getValue(indexValue,fertToWaterMap);
            indexValue = getValue(indexValue,soilToFertMap);
            indexValue = getValue(indexValue,seedToSoilMap);

            if(checkSeed(indexValue,seedMap)){
                return index;
            }
            index = index.add(BigInteger.ONE);

        }
        return null;
    }

    private static boolean checkSeed(BigInteger value, HashMap<BigInteger,DestinationRangeMap> hashMap){
        for(BigInteger key: hashMap.keySet()){
            BigInteger destination = hashMap.get(key).getDestination();
            BigInteger count = hashMap.get(key).getCount();
            BigInteger rangeEnd = key.add(count);
            if(value.compareTo(key) >= 0 && value.compareTo(rangeEnd) < 0){
                value = destination.add(value.subtract(key));
                return true;
            }
        }
        return false;
    }
    private static BigInteger getValue(BigInteger value,HashMap<BigInteger,DestinationRangeMap> hashMap){
        for(BigInteger key: hashMap.keySet()){
            BigInteger destination = hashMap.get(key).getDestination();
            BigInteger count = hashMap.get(key).getCount();
            BigInteger rangeEnd = key.add(count);
            if(value.compareTo(key) >= 0 && value.compareTo(rangeEnd) < 0){
                value = destination.add(value.subtract(key));
                return value;
            }
        }
        return value;
    }

    private static void getMapping(BufferedReader reader,HashMap<BigInteger,DestinationRangeMap> hashMap) throws IOException {
        String line;
        BigInteger destination;
        BigInteger numberOfValues;
        BigInteger source;
        line= reader.readLine();
        line= reader.readLine();
        while(line != null && !line.isEmpty() )
        {
            Pattern pattern = Pattern.compile("\\b\\d+\\b");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            destination = new BigInteger(matcher.group());
            matcher.find();
            source = new BigInteger(matcher.group());
            matcher.find();
            numberOfValues = new BigInteger(matcher.group());
            hashMap.put(source,new DestinationRangeMap(destination,numberOfValues));
            line = reader.readLine();
        }
    }

    private static void getMappingPart2(BufferedReader reader,HashMap<BigInteger,DestinationRangeMap> hashMap) throws IOException {
        String line;
        BigInteger destination;
        BigInteger numberOfValues;
        BigInteger source;
        line= reader.readLine();
        line= reader.readLine();
        while(line != null && !line.isEmpty() )
        {
            Pattern pattern = Pattern.compile("\\b\\d+\\b");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            destination = new BigInteger(matcher.group());
            matcher.find();
            source = new BigInteger(matcher.group());
            matcher.find();
            numberOfValues = new BigInteger(matcher.group());
            hashMap.put(destination,new DestinationRangeMap(source,numberOfValues));
            line = reader.readLine();
        }
    }
}
