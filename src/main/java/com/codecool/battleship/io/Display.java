package com.codecool.battleship.io;

import java.util.Scanner;

public class Display {
    public static void main(String[] args) {
        System.out.println("""
                WELCOME TO THE BATTLESHIP GAME.
                PRESS --> 1. PLAYER VS. PLAYER
                PRESS --> 2. PLAYER VS. COMPUTER
                PRESS --> 0. EXIT
                """);
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        switch (mode) {
            case 1:
                playerVsPlayer();
                break;
            case 2:
                playerVsComputer();
                break;
            case 0:
                exit();
                break;

        }

    }
}
