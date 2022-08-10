package com.codecool.battleship.board;

import com.codecool.battleship.io.Input;
import com.codecool.battleship.players.AbstractPlayer;
import com.codecool.battleship.ships.Ship;

import java.util.Random;

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
    public void randomPlacement(AbstractPlayer player, Board board, int shipSize) {
        Ship ship = new Ship();
        Random random = new Random();

        int[] coords = new int[2];
        coords[0] = random.nextInt(0, board.getBoardSize() - 1);
        System.out.println("Coord od zera " + coords[0]);
        coords[1] = random.nextInt(0, board.getBoardSize() - 1);
        System.out.println("Coord od jedynki " + coords[1]);
//            pobranie kierunku + validacja
        int x = coords[0], y = coords[1];
////        ocean[x][y].setSquareStatus(SquareStatus.S_SHIP);
//        boolean spaceForVertical = input.isSpaceForVertical(coords, board, board.getBoardSize(), shipSize);
//        boolean spaceForHorizontal = input.isSpaceForHorizontal(coords, board, board.getBoardSize(), shipSize);

        boolean horizontal = true;

//        if (shipSize != 1 && spaceForVertical && spaceForHorizontal) {
//            String direction = input.askForDirection();
//            horizontal = direction.equals("1"); // 1 - horizontal | 2 - vertical
//        } else if (spaceForVertical) {
//            horizontal = false;
//        }

        // TODO: REFACTOR
        for (int i = 0; i < shipSize; i++) {
            if (horizontal) {
                try {
                    ocean[x + i][y].setSquareStatus(SquareStatus.S_SHIP);
                    ship.addCords(new Square(x + i, y, SquareStatus.S_SHIP));
                } catch (Exception e) {
                    ocean[x][y + i].setSquareStatus(SquareStatus.S_SHIP);
                    ship.addCords(new Square(x, y + i, SquareStatus.S_SHIP));
                }
            } else {
                ocean[x][y + i].setSquareStatus(SquareStatus.S_SHIP);
                ship.addCords(new Square(x, y + i, SquareStatus.S_SHIP));
            }
        }
        player.addShip(ship); // add ship to player
    }

    @Override
    public void manualPlacement(AbstractPlayer player, Board board, int shipSize, Input input) {

        Ship ship = new Ship();

        int[] coords = input.askForCords("Place your ship", board, BOARD_SIZE, shipSize);
//            pobranie kierunku + validacja
        int x = coords[0], y = coords[1];
//        ocean[x][y].setSquareStatus(SquareStatus.S_SHIP);
        boolean spaceForVertical = input.isSpaceForVertical(coords, board, board.getBoardSize(), shipSize);
        boolean spaceForHorizontal = input.isSpaceForHorizontal(coords, board, board.getBoardSize(), shipSize);

        boolean horizontal = true;

        if (shipSize != 1 && spaceForVertical && spaceForHorizontal) {
            String direction = input.askForDirection();
            horizontal = direction.equals("1"); // 1 - horizontal | 2 - vertical
        } else if (spaceForVertical) {
            horizontal = false;
        }

        for (int i = 0; i < shipSize; i++) {
            if (horizontal) {
                ocean[x + i][y].setSquareStatus(SquareStatus.S_SHIP);
                ship.addCords(new Square(x + i, y, SquareStatus.S_SHIP));
            } else {
                ocean[x][y + i].setSquareStatus(SquareStatus.S_SHIP);
                ship.addCords(new Square(x, y + i, SquareStatus.S_SHIP));
            }
        }
        player.addShip(ship); // add ship to player
    }
}
