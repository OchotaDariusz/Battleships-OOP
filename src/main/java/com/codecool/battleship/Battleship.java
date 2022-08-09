package com.codecool.battleship;

import com.codecool.battleship.io.Display;
import com.codecool.battleship.io.Input;

public class Battleship {

    private final Display display = new Display();
    private final Input input = new Input();
    private int gameMode;  // 1 - player vs player | 2 - player vs computer
    private int gamePhase;  // 1 - placement phase | 2 - shooting phase

    public int getGamePhase() {
        return gamePhase;
    }

    public void setGamePhase(int gamePhase) {
        this.gamePhase = gamePhase;
    }

    private int getGameMode() {
        return gameMode;
    }

    private void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    private void mainLoop() {
        int playerId = 1;
        System.out.println("Main Loop");
        while (!checkIfWon()) {
            makeMove(playerId);

            if (checkIfHit()) {
                if (checkIfShipSunk()) {
                    //update ship character to Sunk and take from players ship that sunken ship
                    System.out.println("Ship has been sunken");
                }
            } else {
                playerId = (playerId == 1) ? 2 : 1;
            }
        }
        displayHighScores(playerId);
        // press any key to end game
        exit();
    }

    private void displayMenu() {
        System.out.println("Display Menu");
//        display.printMenu();
    }

    public void startGame() {
        System.out.println("Start Game");
        displayMenu();
        chooseGameMode(this);
        mainLoop();
    }

    private void displayHighScores(int playerId) {
        System.out.println("High scores");
//        display.printWinner();
    }

    private void exit() {
        System.exit(0);
    }

    private void makeMove(int playerId) {
        System.out.println("Make Move(Play Turn)");
        if (getGamePhase() == 1) {
            System.out.println("placement phase");
            // placement phase
            // get input -> place ship * amount of ships
        } else {
            System.out.println("shooting phase");
            // shooting phase
            // get input
        }

    }

    private boolean checkIfHit() {
        return false;
    }

    private boolean checkIfShipSunk() {
        return false;
    }

    private boolean checkIfWon() {
        return false;
    }

    private void chooseGameMode(Battleship battleship) {
        System.out.println("Game mode");
        int gameMode = 1; // TODO: scanner get input
        battleship.setGameMode(gameMode);
    }
}
