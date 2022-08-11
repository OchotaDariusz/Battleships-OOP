package com.codecool.battleship.players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class AbstractComputerPlayer extends AbstractPlayer {

    private final List<int[]> usedFields = new ArrayList<>();
    private final Random RANDOM = new Random();

    public Random getRANDOM() {
        return RANDOM;
    }

    public void addUsedField(int[] coords) {
        usedFields.add(coords);
    }

    public abstract int[] getRandomCoords(int boardSize);

    public boolean checkIfUsed(int[] coords) {
        for (int[] field : usedFields) {
            if (Arrays.equals(field, coords)) {
                return true;
            }
        }
        return false;
    }

}