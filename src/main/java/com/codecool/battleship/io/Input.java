package com.codecool.battleship.io;

import java.util.Scanner;

public class Input {

    public int askForBoardSize() {
        System.out.println("Please choose a valid board size: (10-20)");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        while (size < 10 || size > 20) {
            size = scanner.nextInt();
        }
        return size;
    }

    public String askForInput() {
        System.out.println("Please enter coordinates: ");
        Scanner coordinates = new Scanner(System.in);
        return coordinates.nextLine().toUpperCase();
    }

    public boolean validateInput(String input, int size) {
        try {
            int[] coords = convertCoords(input);
            return ((coords[0] <= size - 1 && coords[0] >= 0) &&
                    (coords[1] <= size - 1 && coords[1] >= 0));
        } catch (Exception e) {
            return false;
        }
    }

    private int[] convertCoords(String input) {
        int column = input.charAt(0) - 'A';
        int row = Integer.parseInt(input.substring(1)) - 1;
        int[] coords = new int[2];
        coords[0] = column;
        coords[1] = row;
        return coords;
    }

}
