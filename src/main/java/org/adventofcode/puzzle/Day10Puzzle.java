package org.adventofcode.puzzle;


import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class Day10Puzzle {
    public static int pipeMaze(int part) throws IOException {
        return switch (part) {
            case 1 -> getFarthestDistancePart1();
            case 2 -> getEnclosedTilePart2();
            default -> 0;
        };
    }

    public enum CompassDirection {
        N, E, S, W, NE, SE, SW, NW;

    }
    public static int getFarthestDistancePart1() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle10input"));
        String line;
        String input = "";
        while ((line = reader.readLine()) != null) {
            input = input.concat(line).concat("\n");
        }

        var pipeMap = constructPipeMap(input);
        var loopDistances = constructLoopDistances(pipeMap);

        return Integer.parseInt(String.valueOf(loopDistances.values().stream().max(Integer::compareTo).orElseThrow()));
    }

    public static int getEnclosedTilePart2() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("src/main/resources/puzzle10input"));
        String line;
        String input = "";
        while ((line = reader.readLine()) != null) {
            input = input.concat(line).concat("\n");
        }
        var pipeMap = constructPipeMap(input);
        var loopDistances = constructLoopDistances(pipeMap);
        var count = 0;
        var loopPipes = loopDistances.keySet();

        for (var y = 1; y < pipeMap.lines - 1; y++) {
            for (var x = 1; x < pipeMap.columns - 1; x++) {
                if (loopPipes.contains(new Point(x, y))) {
                    continue;
                }

                // Check if there is odd number of pipes cross to the right, if so - we're inside the loop
                var loopPipeCrossingCount = 0;
                var insidePipe = false;
                CompassDirection pipeCameFrom = null;

                for (var i = x + 1; i < pipeMap.columns; i++) {
                    var p = new Point(i, y);

                    if (!loopPipes.contains(p)) {
                        continue;
                    }

                    var pipe = pipeMap.grid.get(p);

                    if (pipe == Pipe.V) {
                        loopPipeCrossingCount++;
                        continue;
                    }

                    // Here we go "inside" the pipe and store which direction it came from
                    if (pipe == Pipe.NE || pipe == Pipe.SE) {
                        insidePipe = true;
                        pipeCameFrom = pipe == Pipe.NE ? CompassDirection.N : CompassDirection.S;
                        continue;
                    }

                    // If we go outside, and it goes in the same direction, it's not counted as crossing
                    if (insidePipe && pipe != Pipe.H) {
                        loopPipeCrossingCount += (pipe.name().startsWith(pipeCameFrom.name()) ? 0 : 1);
                        insidePipe = false;
                        pipeCameFrom = null;
                    }
                }

                if (loopPipeCrossingCount % 2 != 0) {
                    count++;
                }
            }
        }

        return count;
    }

    private static PipeMap constructPipeMap(String input) {
        var lines = input.trim().split("\n");
        var grid = new HashMap<Point, Pipe>();
        Point start = null;

        for (var y = 0; y < lines.length; y++) {
            for (var x = 0; x < lines[y].length(); x++) {
                var charAt = lines[y].charAt(x);

                if (charAt == '.') {
                    continue;
                }

                grid.put(new Point(x, y), switch (charAt) {
                    case 'S' -> Pipe.S;
                    case '|' -> Pipe.V;
                    case '-' -> Pipe.H;
                    case 'F' -> Pipe.SE;
                    case '7' -> Pipe.SW;
                    case 'L' -> Pipe.NE;
                    case 'J' -> Pipe.NW;
                    default -> throw new RuntimeException("Unknown pipe: " + charAt);
                });

                if (charAt == 'S') {
                    start = new Point(x, y);
                }
            }
        }

        return new PipeMap(grid, lines.length, lines[0].length(), start);
    }

    private static Map<Point, Integer> constructLoopDistances(PipeMap pipeMap) {
        var stepsToLoopPoint = new HashMap<Point, Integer>();
        stepsToLoopPoint.put(pipeMap.start, 0);
        CompassDirection sVerticalConnection = null;
        CompassDirection sHorizontalConnection = null;

        var queue = new LinkedList<Position>();
        queue.add(new Position(pipeMap.start, 0));

        while (!queue.isEmpty()) {
            var position = queue.removeFirst();
            var pipe = pipeMap.grid.get(position.point);

            if (stepsToLoopPoint.containsKey(position.point) && stepsToLoopPoint.get(position.point) < position.steps) {
                continue;
            }

            stepsToLoopPoint.put(position.point, position.steps);

            // Check if there is north connection
            if (pipe == Pipe.V || pipe == Pipe.NE || pipe == Pipe.NW || pipe == Pipe.S) {
                var next = new Point(position.point.x, position.point.y - 1);
                var nextPipe = pipeMap.grid.get(next);

                if ((nextPipe == Pipe.V || nextPipe == Pipe.SE || nextPipe == Pipe.SW)) {
                    queue.add(new Position(next, position.steps + 1));

                    if (pipe == Pipe.S) {
                        sVerticalConnection = CompassDirection.N;
                    }
                }
            }

            // Check if there is south connection
            if (pipe == Pipe.V || pipe == Pipe.SE || pipe == Pipe.SW || pipe == Pipe.S) {
                var next = new Point(position.point.x, position.point.y + 1);
                var nextPipe = pipeMap.grid.get(next);

                if ((nextPipe == Pipe.V || nextPipe == Pipe.NE || nextPipe == Pipe.NW)) {
                    queue.add(new Position(next, position.steps + 1));

                    if (pipe == Pipe.S) {
                        sVerticalConnection = CompassDirection.S;
                    }
                }
            }

            // Check if there is east connection
            if (pipe == Pipe.H || pipe == Pipe.NE || pipe == Pipe.SE || pipe == Pipe.S) {
                var next = new Point(position.point.x + 1, position.point.y);
                var nextPipe = pipeMap.grid.get(next);

                if ((nextPipe == Pipe.H || nextPipe == Pipe.NW || nextPipe == Pipe.SW)) {
                    queue.add(new Position(next, position.steps + 1));

                    if (pipe == Pipe.S) {
                        sHorizontalConnection = CompassDirection.E;
                    }
                }
            }

            // Check if there is west connection
            if (pipe == Pipe.H || pipe == Pipe.NW || pipe == Pipe.SW || pipe == Pipe.S) {
                var next = new Point(position.point.x - 1, position.point.y);
                var nextPipe = pipeMap.grid.get(next);

                if ((nextPipe == Pipe.H || nextPipe == Pipe.NE || nextPipe == Pipe.SE)) {
                    queue.add(new Position(next, position.steps + 1));

                    if (pipe == Pipe.S) {
                        sHorizontalConnection = CompassDirection.W;
                    }
                }
            }
        }

        // Depending on the Start point connections, we can determine the pipe type
        if (sVerticalConnection != null && sHorizontalConnection != null) {
            pipeMap.grid.put(pipeMap.start, Pipe.valueOf(sVerticalConnection.name() + sHorizontalConnection.name()));
        } else if (sHorizontalConnection == null) {
            pipeMap.grid.put(pipeMap.start, Pipe.V);
        } else {
            pipeMap.grid.put(pipeMap.start, Pipe.H);
        }

        return stepsToLoopPoint;
    }

    private record PipeMap(HashMap<Point, Pipe> grid, int lines, int columns, Point start) {}
    enum Pipe { V, H, SE, SW, NE, NW, S }

    private static class Position {
        public Point point;
        public int steps;

        public Position(Point point, int steps) {
            this.point = point;
            this.steps = steps;
        }
    }
}



