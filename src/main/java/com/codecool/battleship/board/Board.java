package com.codecool.battleship.board;

import javax.swing.text.Segment;

public class Board {

    final int BOARD_SIZE = 10;

    private Square[][] ocean;
    public Board() {
        ocean = new Square[BOARD_SIZE][BOARD_SIZE];
        for (int x=0; x<BOARD_SIZE; x++)
            for (int y=0; y<BOARD_SIZE; y++)
                ocean[x][y] = new Square(x, y, Square.Status.S_EMPTY);
    }

    boolean isPlacementOk(int px, int py) {

        // check if there's a water around our new ship
        for (int x = px-1; x<px+1; x++) {
            for (int y = py-1; y<py+1; y++) {
                x = Math.min(BOARD_SIZE-1, Math.max(0, x));
                y = Math.min(BOARD_SIZE-1, Math.max(0, y));
                if (ocean[x][y].getSquareStatus() != Square.Status.S_EMPTY)
                    return false;
            }
        }
        return true;
    }

    public boolean shipPlace(int x, int y) {
        if (!this.isPlacementOk(x, y)) {
            return false;
        }
        ocean[x][y] = new Square(x, y, Square.Status.S_SHIP);
        return true;
    }

    @Override
    public String toString() {
        String board = "";

        // top legend
        board += " " + "   ";
        for (int x = 0; x < BOARD_SIZE; x++)
            board += " " + Character.toString('A'+x).toString() + "  ";
        board += "\n";

        // board with leftside legend
        for (int x = 0; x < BOARD_SIZE+1; x++)
        {
            if (x == 0)
                board += "   ┌──";
            if (x == BOARD_SIZE)
                board += "─┐";
            if (x > 0 && x < BOARD_SIZE)
                board += "─┬──";
        }
        board += "\n";

        for (int y = 0; y < BOARD_SIZE; y++)
        {
            String label = String.valueOf(y+1);
            if (label.length() < 2)
                label = " "+label;

            // main loop, place symbols / y axis labels here
            for (int x = 0; x < BOARD_SIZE+1; x++) {
                if (x==0)
                    board += label + " │ " + ocean[x][y].toString();
                if (x > 0 && x < BOARD_SIZE)
                    board += " │ "+ ocean[x][y].toString();
                if (x == BOARD_SIZE)
                    board += " │";
            }
            board += "\n";

            if (y == BOARD_SIZE - 1)
                break;
            for (int x = 0; x < BOARD_SIZE+1; x++)
            {
                if (x==0)
                    board += "   ├──";
                if (x == BOARD_SIZE)
                    board += "─┤";
                if (x > 0 && x < BOARD_SIZE)
                    board += "─┼──";
            }
            board += "\n";
        }

        for (int x = 0; x < BOARD_SIZE+1; x++)
        {
            if (x == 0)
                board += "   └──";
            if (x == BOARD_SIZE)
                board += "─┘";
            if (x > 0 && x < BOARD_SIZE)
                board += "─┴──";
        }
        return board;


        /*
        ret += "    ";
        for (int x = 0; x < BOARD_SIZE; x++)
        {
            char label = 'A';
            ret += Character.toString(label+x) + " ";
        }
        ret += "\n";

        for (int y=0; y<BOARD_SIZE; y++)
        {
            String label = String.valueOf(y+1);
            if (label.length() < 2)
                label = " "+label;

            ret += label+ " ";

            ret += "|";
            for (int x=0; x<BOARD_SIZE; x++)
            {
                ret += " " + ocean[x][y].toString() + " |";
            }
            ret += "\n";

        }*/

       // return ret;
    }
}
