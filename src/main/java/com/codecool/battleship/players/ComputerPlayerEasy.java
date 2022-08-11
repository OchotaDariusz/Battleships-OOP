
package com.codecool.battleship.players;

import java.util.List;

public class ComputerPlayerEasy extends AbstractComputerPlayer {

    @Override
    public int[] getRandomCoords(int boardSize) {
        int[] coords = new int[2];

        do {
            coords[0] = getRANDOM().nextInt(1, boardSize);
            coords[1] = getRANDOM().nextInt(1, boardSize);
        } while (checkIfUsed(coords));

        addUsedField(coords);
        return coords;
    }
}