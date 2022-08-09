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
            int column = convert
        }
    }

}
