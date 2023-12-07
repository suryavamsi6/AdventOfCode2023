package org.adventofcode.models;

import java.util.ArrayList;
import java.util.Objects;

public class Hand implements Comparable {
    public ArrayList<Integer> card = new ArrayList<>();
    int cardStrength;

    public Hand(ArrayList<Integer> card, int cardStrength) {
        this.card = card;
        this.cardStrength = cardStrength;
    }

    @Override
    public int compareTo(Object o) {
        Hand other = (Hand) o;
        if (cardStrength != other.cardStrength) {
            return cardStrength - other.cardStrength;
        } else {
            for (int i = 0; i < card.size(); i++) {
                if (!Objects.equals(card.get(i), other.card.get(i))) {
                    return card.get(i) - other.card.get(i);
                }
            }
            return 0;
        }
    }
}
