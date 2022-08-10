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

    public void printPlacementOption() {
        System.out.println("""
                PRESS --> 1. To place ships manually
                PRESS --> 2. To place ships randomly
                """);
    }

    public void printBoard(Board board1, Board board2) {
        System.out.println("Board");
        printBoardHeaders(board1.getBoardSize());
        printBoardBody(board1, board2, board1.getBoardSize());
        printBoardBottom(board1.getBoardSize());
    }

    private void printBoardHeaders(int boardSize) {
        StringBuilder boardHeaders = new StringBuilder("    ");
        for (int x = 0; x < boardSize; x++)
            boardHeaders.append(" ").append(Character.toString('A' + x).toString()).append("  ");
        boardHeaders.append("         ");
        for (int x = 0; x < boardSize; x++)
            boardHeaders.append(" ").append(Character.toString('A' + x).toString()).append("  ");
        boardHeaders.append("\n");

        for (int x = 0; x < boardSize + 1; x++) {
            if (x == 0)
                boardHeaders.append("   ┌──");
            if (x == boardSize)
                boardHeaders.append("─┐");
            if (x > 0 && x < boardSize)
                boardHeaders.append("─┬──");
        }
        boardHeaders.append("     ");
        for (int x = 0; x < boardSize + 1; x++) {
            if (x == 0)
                boardHeaders.append("   ┌──");
            if (x == boardSize)
                boardHeaders.append("─┐");
            if (x > 0 && x < boardSize)
                boardHeaders.append("─┬──");
        }

        System.out.println(boardHeaders);
    }

    private void printBoardBody(Board board1, Board board2, int boardSize) {
        StringBuilder boardBody = new StringBuilder();
        for (int y = 0; y < boardSize; y++) {
            String label = String.valueOf(y + 1);
            if (label.length() < 2)
                label = " " + label;

            // main loop, place symbols / y axis labels here
            for (int x = 0; x < boardSize + 1; x++) {
                if (x == 0)
                    boardBody.append(label).append(" │ ").append(board1.getOcean()[x][y].toString());
                if (x > 0 && x < boardSize)
                    boardBody.append(" │ ").append(board1.getOcean()[x][y].toString());
                if (x == boardSize)
                    boardBody.append(" │");
            }
            boardBody.append("     ");

            for (int x = 0; x < boardSize + 1; x++) {
                if (x == 0)
                    boardBody.append(label).append(" │ ").append(board2.getOcean()[x][y].toString());
                if (x > 0 && x < boardSize)
                    boardBody.append(" │ ").append(board2.getOcean()[x][y].toString());
                if (x == boardSize)
                    boardBody.append(" │");
            }

            boardBody.append("\n");

            // bottom line(inner body)
            if (y == boardSize - 1)
                break;
            for (int x = 0; x < boardSize + 1; x++) {
                if (x == 0)
                    boardBody.append("   ├──");
                if (x == boardSize)
                    boardBody.append("─┤");
                if (x > 0 && x < boardSize)
                    boardBody.append("─┼──");
            }

            boardBody.append("     ");
            if (y == boardSize - 1)
                break;
            for (int x = 0; x < boardSize + 1; x++) {
                if (x == 0)
                    boardBody.append("   ├──");
                if (x == boardSize)
                    boardBody.append("─┤");
                if (x > 0 && x < boardSize)
                    boardBody.append("─┼──");
            }
            if (y != boardSize - 1) boardBody.append("\n");
        }
        System.out.print(boardBody);
    }

    private void printBoardBottom(int boardSize) {
        StringBuilder boardBottom = new StringBuilder();
        for (int x = 0; x < boardSize + 1; x++) {
            if (x == 0)
                boardBottom.append("   └──");
            if (x == boardSize)
                boardBottom.append("─┘");
            if (x > 0 && x < boardSize)
                boardBottom.append("─┴──");
        }
        boardBottom.append("     ");
        for (int x = 0; x < boardSize + 1; x++) {
            if (x == 0)
                boardBottom.append("   └──");
            if (x == boardSize)
                boardBottom.append("─┘");
            if (x > 0 && x < boardSize)
                boardBottom.append("─┴──");
        }
        System.out.println(boardBottom);
    }

    public void printGameplay() {
        System.out.println("shoot miss hit");
    }

    public void printWinner(int playerId) {

    }

    public void print(String string) {
        System.out.println(string);
    }

}
