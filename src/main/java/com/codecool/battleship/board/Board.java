package com.codecool.battleship.board;


import com.codecool.battleship.io.Input;

public class Board implements BoardFactory {

    final int BOARD_SIZE = 10;

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    private final Square[][] ocean;

    public Square[][] getOcean() {
        return ocean;
    }

    public Board() {
        ocean = new Square[BOARD_SIZE][BOARD_SIZE];
        for (int x = 0; x < BOARD_SIZE; x++)
            for (int y = 0; y < BOARD_SIZE; y++)
                ocean[x][y] = new Square(x, y, SquareStatus.S_EMPTY);
    }

    boolean isPlacementOk(int px, int py) {

        // check if there's a water around our new ship
        for (int x = px - 1; x < px + 1; x++) {
            for (int y = py - 1; y < py + 1; y++) {
                x = Math.min(BOARD_SIZE - 1, Math.max(0, x));
                y = Math.min(BOARD_SIZE - 1, Math.max(0, y));
                if (ocean[x][y].getSquareStatus() != SquareStatus.S_EMPTY)
                    return false;
            }


        }
        return true;
    }

    public boolean shipPlace(int x, int y) {
        if (!this.isPlacementOk(x, y)) {
            return false;
        }
        ocean[x][y] = new Square(x, y, SquareStatus.S_SHIP);
        return true;
    }

    @Override
    public void randomPlacement(int shipSize) {

    }

    @Override
    public void manualPlacement(Board board, int shipSize, Input input) {
        int[] coords = input.askForCords("Place your ship", board, BOARD_SIZE, shipSize);
//            pobranie kierunku + validacja
        int x = coords[0], y = coords[1];
        ocean[x][y].setSquareStatus(SquareStatus.S_SHIP);

        boolean horizontal = true; // over
        for (int i = 0; i < shipSize; i++) {
            if (horizontal) {
                ocean[x + i][y].setSquareStatus(SquareStatus.S_SHIP);
            } else {
                ocean[x][y + i].setSquareStatus(SquareStatus.S_SHIP);
            }
        }


    }
}
