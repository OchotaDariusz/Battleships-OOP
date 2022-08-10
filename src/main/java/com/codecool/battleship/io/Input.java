package com.codecool.battleship.io;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.SquareStatus;

import java.util.Scanner;

public class Input {

    public boolean validateForBoardSize(int size) {
        return size < 10 || size > 20;
    }

    public String askForInput(String label) {
        System.out.println(label);
        Scanner coordinates = new Scanner(System.in);
        return coordinates.nextLine().toUpperCase();
    }

    public boolean validateOption(String input) {
        return input.equals("1") || input.equals("2");
    }

    private boolean validateCords(String input, Board board, int boardSize, int shipLength) {
        try {
            int[] coords = convertCoords(input);
            return isOnBoard(coords, boardSize) &&
                    isSpaceForVertical(coords, board, boardSize, shipLength) &&
                    isSpaceForHorizontal(coords, board, boardSize, shipLength);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isOnBoard(int[] coords, int boardSize) {
        try {
            return ((coords[0] <= boardSize - 1 && coords[0] >= 0) &&
                    (coords[1] <= boardSize - 1 && coords[1] >= 0));
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isNotOccupied(Board board, int[] coords) {
        int[] tablica = {-1, 0, 1};
        for (int i = 0; i < tablica.length; i++) {
            for (int j = 0; j < tablica.length; j++) {
                try {
                    if (board.getOcean()[coords[0] + tablica[i]][coords[1] + tablica[j]].getSquareStatus().equals(SquareStatus.S_SHIP)) {
                        System.out.println("Can't place ship here!");
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        return true;
    }

    private boolean isSpaceForHorizontal(int[] coords, Board board, int boardSize, int shipLength) {
        if (coords[0] + shipLength > boardSize - 1) {
            return false;
        }
        for (int i = 0; i < shipLength; i++) {
            coords[0] = coords[0] + i;
            if (!isNotOccupied(board, coords)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSpaceForVertical(int[] coords, Board board, int boardSize, int shipLength) {
        if (coords[1] + shipLength > boardSize - 1) {
            return false;
        }
        for (int i = 0; i < shipLength; i++) {
            coords[1] = coords[1] + i;
            if (!isNotOccupied(board, coords)) {
                return false;
            }
        }
        return true;
    }

    private int[] convertCoords(String input) {
        int column = input.charAt(0) - 'A';
        int row = Integer.parseInt(input.substring(1)) - 1;
        int[] coords = new int[2];
        coords[0] = column;
        coords[1] = row;
        return coords;
    }

    public int[] askForCords(String label, Board board, int boardSize, int shipSize) {
        String input = askForInput(label);

        while (!validateCords(input, board, boardSize, shipSize)) {
            System.out.println("WRONG COORDS");
            input = askForInput(label);
        }
        return convertCoords(input);
    }


}

