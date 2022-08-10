package com.codecool.battleship;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.io.Display;
import com.codecool.battleship.io.Input;
import com.codecool.battleship.players.AbstractPlayer;
import com.codecool.battleship.players.ComputerPlayerEasy;
import com.codecool.battleship.players.UserPlayer;

public class Battleship {

    private final Display display = new Display();
    private final Input input = new Input();
    private int gameMode;  // 1 - player vs player | 2 - player vs computer
    private int gamePhase = 1;  // 1 - placement phase | 2 - shooting phase
    private final AbstractPlayer[] players = new AbstractPlayer[2];
    private final Board playerOneBoard = new Board();
    private final Board playerTwoBoard = new Board();
    private final int[] shipsToPlace = {5, 4, 3, 2, 1};


    private int placementOption;

    public int getGamePhase() {
        return gamePhase;
    }

    public int getPlacementOption() {
        return placementOption;
    }

    public void setPlacementOption(int placementOption) {
        this.placementOption = placementOption;
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
        Board board;
        int playerId = 1;
        System.out.println("Main Loop");
        while (!checkIfWon()) {
            display.printBoard(playerOneBoard, playerTwoBoard);
            board = (playerId == 1) ? playerOneBoard : playerTwoBoard;
            makeMove(board, playerId);

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
        display.printMenu();
    }

    public void startGame() {
        System.out.println("Start Game");
        chooseGameMode(this);
        choosePlacementOption(this);
        mainLoop();
    }

    private void displayHighScores(int playerId) {
        System.out.println("High scores");
        display.printWinner(playerId);

    }

    private void exit() {
        System.exit(0);
    }

    private void makeMove(Board board, int playerId) {
        System.out.println("Make Move(Play Turn)");
        if (getGamePhase() == 1) {
            placementPhase(board, playerId);
        } else {
            System.out.println("shooting phase");
            // shooting phase
            // get input
            String cos = input.askForInput("pauza");
        }

    }

    private void placementPhase(Board board, int playerId) {
        System.out.println("placement phase");
        for (int ship : shipsToPlace) {
            if (playerId == 1) {
                if (placementOption == 1) {
                    playerOneBoard.manualPlacement(board, ship, input);
                } else {
                    playerOneBoard.randomPlacement(ship);
                }
            } else {
                if (placementOption == 1 && gameMode == 1) {
                    playerTwoBoard.manualPlacement(board, ship, input);
                } else {
                    playerTwoBoard.randomPlacement(ship);
                }
            }
        }
        if (playerId == 2 && getGamePhase() == 1) {
            setGamePhase(2);
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
        displayMenu();
        String userInput = input.askForInput("Choose game mode: ");
        int gameMode;
        while (!input.validateOption(userInput)) {
            System.out.println("Wrong input!");
            userInput = input.askForInput("Choose game mode: ");
        }
        gameMode = Integer.parseInt(userInput);
        battleship.setGameMode(gameMode);
        setupPlayers();
    }

    private void choosePlacementOption(Battleship battleship) {
        display.printPlacementOption();
        String option = input.askForInput("Choose placement option: ");
        int placement;
        while (!input.validateOption(option)) {
            System.out.println("Wrong input!");
            option = input.askForInput("Choose placement option: ");
        }
        placement = Integer.parseInt(option);
        setPlacementOption(placement);

    }

    private void setupPlayers() {
        players[0] = new UserPlayer();
        if (getGameMode() == 1) {
            players[1] = new UserPlayer();
        } else {
            players[1] = new ComputerPlayerEasy();
        }
    }

}
