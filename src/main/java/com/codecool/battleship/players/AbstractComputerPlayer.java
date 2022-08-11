package com.codecool.battleship.players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class AbstractComputerPlayer extends AbstractPlayer {

    private final List<int[]> usedFields = new ArrayList<>();
    private final List<int[]> shootedFields = new ArrayList<>();
    private final List<int[]> sunkenShipsFields = new ArrayList<>();
    private final Random RANDOM = new Random();

    public Random getRANDOM() {
        return RANDOM;
    }

    public List<int[]> getShootedFields() {
        return shootedFields;
    }

    public void clearHittedFields() {
        shootedFields.clear();
    }

    public void addShootedFields(int[] coords) {
        shootedFields.add(coords);
    }

    public void addSunkenShipsFields(int[] coords) {
        sunkenShipsFields.add(coords);
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

    public void addToUsedFieldsAfterSunk() {
        int[] fields = {-1, 0, 1};
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                try {
                    for (int[] field : sunkenShipsFields) {
                        field[0] += fields[i];
                        field[1] += fields[j];
                        addUsedField(field);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
    }

}