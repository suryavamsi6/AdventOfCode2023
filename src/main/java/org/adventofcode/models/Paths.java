package org.adventofcode.models;

public class Paths {
    String leftPath;
    String rightPath;

    public Paths(String leftPath, String rightPath) {
        this.leftPath = leftPath;
        this.rightPath = rightPath;
    }

    public String getPath(char direction){
        if(direction == 'L'){
            return leftPath;
        }
        return rightPath;
    }
    public String getLeftPath() {
        return leftPath;
    }

    public void setLeftPath(String leftPath) {
        this.leftPath = leftPath;
    }

    public String getRightPath() {
        return rightPath;
    }

    public void setRightPath(String rightPath) {
        this.rightPath = rightPath;
    }
}
