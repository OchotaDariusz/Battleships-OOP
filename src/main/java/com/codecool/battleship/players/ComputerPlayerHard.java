package com.codecool.battleship.players;

import java.util.Arrays;

public class ComputerPlayerHard extends AbstractComputerPlayer {

    @Override
    public int[] getRandomCoords(int boardSize) {
        int counter = 0;
        int[] coords = new int[2];
        do {
            counter++;
            if (counter == 9) {
                this.clearHittedFields();
            }
            if (getShootedFields().size() > 0) {
                coords = getNearFieldToShoot();
            } else {
                coords[0] = getRANDOM().nextInt(1, boardSize);
                coords[1] = getRANDOM().nextInt(1, boardSize);
            }
        } while (checkIfUsed(coords));

        addUsedField(coords);
        return coords;
    }

    private int[] getNearFieldToShoot() {
        int[] fields = {-1, 0, 1};
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                try {
                    int[] field = getShootedFields().get(getShootedFields().size() - 1);
                    int[] fieldToShoot = new int[2];
                    fieldToShoot[0] = field[0] + fields[i];
                    fieldToShoot[1] = field[1] + fields[j];
                    if (!checkIfUsed(fieldToShoot)) {
                        return fieldToShoot;
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        return new int[]{1, 1};
    }

}
