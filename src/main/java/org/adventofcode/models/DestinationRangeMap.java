package org.adventofcode.models;

import java.math.BigInteger;

public class DestinationRangeMap {
    BigInteger destination;
    BigInteger count;

    public DestinationRangeMap(BigInteger destination, BigInteger count) {
        this.destination = destination;
        this.count = count;
    }

    public BigInteger getDestination() {
        return destination;
    }

    public void setDestination(BigInteger destination) {
        this.destination = destination;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }
}
