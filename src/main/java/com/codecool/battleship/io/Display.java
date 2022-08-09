package com.codecool.battleship.io;

import com.codecool.battleship.board.Board;

public class Display {

    public void printMenu() {
        System.out.println("""
                WELCOME TO THE BATTLESHIP GAME.
                PRESS --> 1. PLAYER VS. PLAYER
                PRESS --> 2. PLAYER VS. COMPUTER
                PRESS --> 0. EXIT
                """);
    }

    public void printBoard(Board board1, Board board2) {
        System.out.println("Board");
    }

    public void printGameplay() {
        System.out.println("shoot miss hit");
    }

    public void printWinner(int playerId) {

    }

}
