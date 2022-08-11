package com.codecool.battleship.io;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.SquareStatus;

import javax.xml.stream.events.Characters;
import java.util.Scanner;

public class Input {

    private final Display display = new Display();

    public boolean validateForBoardSize(int size) {
        return size < 10 || size > 20;
    }

    public String askForDirection() {
        display.print("1 - Horizontal | 2 - Vertical");
        String direction = askForInput("Choose direction:");
        while (!validateOption(direction)) {
            direction = askForInput("Choose direction:");
        }
        return direction;
    }

    public String askForInput(String label) {
        display.print(label);
        Scanner coordinates = new Scanner(System.in);
        return coordinates.nextLine().toUpperCase();
    }

    public int[] askForShootingCoords(String label, int boardSize) {
        display.print(label);
        Scanner coordinates = new Scanner(System.in);
        String input = coordinates.nextLine().toUpperCase();
        while (!validateShoot(input)) {
            display.print("wrong coordinates!");
            input = coordinates.nextLine().toUpperCase();
        }
        int[] coords= convertCoords(input);

        return coords;
    }

    private boolean validateShoot(String coords) {
        if (!String.valueOf(coords.charAt(0)).matches("\\D") || coords.length() > 3 || isDigit(String.valueOf(coords.charAt(0))) ) {

            return false;
        }
        return isDigit(coords.substring(1));

    }
    public boolean isDigit(String input){
        try {
            Integer.parseInt(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public boolean validateOption(String input) {
        return input.equals("1") || input.equals("2");
    }

    private boolean validateCords(String input, Board board, int boardSize, int shipLength) {
        try {
            int[] coords = convertCoords(input);
            return isOnBoard(coords, boardSize) &&
                    (isSpaceForVertical(coords, board, boardSize, shipLength) ||
                            isSpaceForHorizontal(coords, board, boardSize, shipLength));
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
        int[] fields = {-1, 0, 1};
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                try {
                    if (board.getOcean()[coords[0] + fields[i]][coords[1] + fields[j]].getSquareStatus().equals(SquareStatus.S_SHIP)) {
                        display.print("Can't place ship here!");
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        return true;
    }

    public boolean isSpaceForHorizontal(int[] coords, Board board, int boardSize, int shipLength) {
        if (coords[0] + shipLength > boardSize) {
            return false;
        }
        int[] check = new int[2];
        for (int i = 0; i < shipLength; i++) {
            check[0] = coords[0] + i;
            check[1] = coords[1];
            if (!isNotOccupied(board, check)) {
                return false;
            }
        }
        return true;
    }

    public boolean isSpaceForVertical(int[] coords, Board board, int boardSize, int shipLength) {
        if (coords[1] + shipLength > boardSize) {
            return false;
        }
        int[] check = new int[2];
        for (int i = 0; i < shipLength; i++) {
            check[0] = coords[0] + i;
            check[1] = coords[1];

            if (!isNotOccupied(board, check)) {
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
            display.print("WRONG COORDS");
            input = askForInput(label);
        }
        return convertCoords(input);
    }

}

