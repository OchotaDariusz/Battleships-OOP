package com.codecool.battleship.board;

import com.codecool.battleship.io.Input;
import com.codecool.battleship.players.AbstractPlayer;
import com.codecool.battleship.ships.Ship;

import java.util.Random;

public class Board implements BoardFactory {

    private final int BOARD_SIZE = 10;

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

    @Override
    public void randomPlacement(AbstractPlayer player, Board board, int shipSize, Input input) {
        Ship ship = new Ship();
        Random random = new Random();

        int[] coords = new int[2];
        boolean flag = true;
        boolean horizontal;
        boolean vertical;
        do {
            coords[0] = random.nextInt(0, board.getBoardSize() - 1);
            coords[1] = random.nextInt(0, board.getBoardSize() - 1);
            horizontal = input.isSpaceForHorizontal(coords, board, board.getBoardSize(), shipSize);
            vertical = input.isSpaceForVertical(coords, board, board.getBoardSize(), shipSize);
            if (horizontal || vertical) flag = false;
        } while (flag);
        int x = coords[0], y = coords[1];
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

    @Override
    public void manualPlacement(AbstractPlayer player, Board board, int shipSize, Input input) {

        Ship ship = new Ship();

        int[] coords = input.askForCords("Place your ship", board, BOARD_SIZE, shipSize);
        int x = coords[0], y = coords[1];
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
