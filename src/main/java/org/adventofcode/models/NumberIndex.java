package org.adventofcode.models;

public class NumberIndex {
    int number;
    int startIndex;
    int endIndex;

    boolean isPartNumber = false;
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public boolean isPartNumber() {
        return isPartNumber;
    }

    public void setIsPartNumber(boolean partNumber) {
        isPartNumber = partNumber;
    }
}
