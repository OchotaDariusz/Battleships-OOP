package com.codecool.battleship.board;

import javax.swing.text.Segment;

public class Board {

    final int BOARD_SIZE = 10;

    private Square[][] ocean;
    public Board() {
        ocean = new Square[BOARD_SIZE][BOARD_SIZE];
        for (int x=0; x<BOARD_SIZE; x++)
            for (int y=0; y<BOARD_SIZE; y++)
                ocean[x][y] = new Square(x,y, Square.Status.S_EMPTY);
    }

    private int[] last_placed_segment = new int[]{-1,-1};   // last_placed_segment[0] = x, [1] = y
    boolean isPlacementOk(int px, int py) {
        boolean can_place = ocean[px][py].getSquareStatus() == Square.Status.S_EMPTY;
        for (int x = px-1; x<px+1; x++) {
            for (int y = py-1; y<py+1; y++) {
                if (x == last_placed_segment[0] && y == last_placed_segment[1])
                    continue;

                x = Math.min(BOARD_SIZE-1, Math.max(0, x));
                y = Math.min(BOARD_SIZE-1, Math.max(0, y));
                can_place = can_place && ocean[x][y].getSquareStatus() == Square.Status.S_EMPTY;
                if (!can_place)
                    break;
            }
        }
        if (!can_place)
            return false;

        can_place = last_placed_segment[0] == -1 && last_placed_segment[1] == -1;
        if (!can_place && py == last_placed_segment[1])
        {
            if (px == last_placed_segment[0]-1 || px == last_placed_segment[0]+1)
                can_place = true;
        }
        if (!can_place && px == last_placed_segment[0])
        {
            if (py == last_placed_segment[1]-1 || py == last_placed_segment[1]+1)
                can_place = true;
        }
        return can_place;
    }

    public boolean shipPlace(int x, int y) {
        if (!this.isPlacementOk(x, y)) {
            return false;
        }
        ocean[x][y] = new Square(x, y, Square.Status.S_SHIP);
        last_placed_segment = new int[]{x,y};
        return true;
    }

    public boolean shipCommit() {
        last_placed_segment = new int[]{-1, -1};
        return true;
    }
}
