package com.codecool.battleship;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.io.Display;
import com.codecool.battleship.io.Input;
import com.codecool.battleship.players.*;
import com.codecool.battleship.ships.ShipType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Battleship {

    private final Display display = new Display();
    private final Input input = new Input();
    private int gameMode;  // 1 - player vs player | 2 - player vs computer
    private int gamePhase = 1;  // 1 - placement phase | 2 - shooting phase
    private int difficulty;
    private final AbstractPlayer[] players = new AbstractPlayer[2];
    private final Board emptyBoard = new Board();
    private final Board emptyBoard2 = new Board();
    private final Board playerOneBoard = new Board();
    private final Board playerTwoBoard = new Board();


    private final List<ShipType> shipTypeList = new ArrayList<ShipType>(
            Arrays.asList(ShipType.DESTROYER, ShipType.SUBMARINE, ShipType.BATTLESHIP,
                    ShipType.CRUISER, ShipType.CARRIER)
    );

    private int placementOption;

    private int getGamePhase() {
        return gamePhase;
    }

    private void setPlacementOption(int placementOption) {
        this.placementOption = placementOption;
    }

    private void setGamePhase(int gamePhase) {
        this.gamePhase = gamePhase;
    }

    private int getDifficulty() {
        return difficulty;
    }

    private void setDifficulty(String difficulty) {
        if (difficulty.equals("1")) this.difficulty = 1;
        else if (difficulty.equals("2")) this.difficulty = 2;
        else this.difficulty = 3;
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
        boolean flag;
        while (!checkIfWon(playerId)) {
            if (getGamePhase() == 2) {
                if (playerId == 1) display.printBoard(playerOneBoard, emptyBoard);
                else display.printBoard(emptyBoard2, playerTwoBoard);
            }
            board = (playerId == 1) ? playerOneBoard : playerTwoBoard;
            if (playerId == 1) {
                flag = makeMove(board, playerId, players[0]);
            } else {
                flag = makeMove(board, playerId, players[1]);
            }

            if (!flag) {
                playerId = (playerId == 1) ? 2 : 1;
            }
        }
        displayHighScores(playerId);
        exit();
    }

    private void displayMenu() {
        display.printMenu();
    }

    public void startGame() {
        chooseGameMode(this);
        if (getGameMode() == 2) chooseGameDifficulty();
        mainLoop();
    }

    private void displayHighScores(int playerId) {
        display.printWinner(playerId);
    }

    private void exit() {
        System.exit(0);
    }

    private boolean makeMove(Board board, int playerId, AbstractPlayer player) {
        display.print("Make Move(Play Turn)");
        if (getGamePhase() == 1) {
            placementPhase(board, playerId);
        } else {
            display.print("Shooting phase");
            int[] shootCoords;
            if (getGameMode() == 2 && playerId == 2) {
                // random coords for Computer AI
                shootCoords = ((AbstractComputerPlayer) players[1]).getRandomCoords(board.getBoardSize());
            } else {
                shootCoords = input.askForShootingCoords("Choose field to shoot", board.getBoardSize());
            }
            if (playerId == 1) {
                return player.makeMove(playerTwoBoard, emptyBoard, shootCoords, players[1]);
            } else {
                if (getGameMode() == 2) {
                    boolean hitted = player.makeMove(playerOneBoard, emptyBoard2, shootCoords, players[0]);
                    if (hitted) {
                        ((AbstractComputerPlayer) player).addShootedFields(shootCoords);
                    }
                    return hitted;
                }
                return player.makeMove(playerOneBoard, emptyBoard2, shootCoords, players[0]);
            }
        }
        return false;
    }

    private void placementPhase(Board board, int playerId) {
        display.print("Placement phase");
        for (ShipType ship : shipTypeList) {
            if (playerId == 1) display.printBoard(playerOneBoard, emptyBoard);
            else display.printBoard(emptyBoard, playerTwoBoard);
            if (playerId == 1) {
                if (placementOption == 1) {
                    playerOneBoard.manualPlacement(players[playerId - 1], board, ship.getShipLength(), input);
                } else {
                    playerOneBoard.randomPlacement(players[playerId - 1], board, ship.getShipLength(), input);
                }
            } else {
                if (placementOption == 1 && gameMode == 1) {
                    playerTwoBoard.manualPlacement(players[playerId - 1], board, ship.getShipLength(), input);
                } else {
                    playerTwoBoard.randomPlacement(players[playerId - 1], board, ship.getShipLength(), input);
                }
            }
        }

        if (playerId == 2 && getGamePhase() == 1) {
            setGamePhase(2);
        }
    }

    private boolean checkIfWon(int playerId) {
        if (getGamePhase() == 2) {
            if (playerId == 1) {
                return !players[1].isAlive();
            } else {
                return !players[0].isAlive();
            }
        }
        return false;
    }

    private void chooseGameMode(Battleship battleship) {
        displayMenu();
        String userInput = input.askForInput("Choose game mode: ");
        int gameMode;
        while (!input.validateOption(userInput)) {
            display.print("Wrong input!");
            userInput = input.askForInput("Choose game mode: ");
        }
        if (userInput.equals("0")) exit();
        gameMode = Integer.parseInt(userInput);
        battleship.setGameMode(gameMode);
        setupPlayers();
    }

    private void chooseGameDifficulty() {
        display.print("1 - EASY | 2 - MEDIUM | 0 - HARD");
        String userInput = input.askForInput("Choose game difficulty: ");
        while (!input.validateOption(userInput)) {
            display.print("Wrong input!");
            userInput = input.askForInput("Choose game difficulty: ");
        }
        setDifficulty(userInput);
    }

    private void choosePlacementOption(Battleship battleship) {
        display.printPlacementOption();
        String option = input.askForInput("Choose placement option: ");
        int placement;
        while (!input.validateOption(option) && !option.equals("0")) {
            display.print("Wrong input!");
            option = input.askForInput("Choose placement option: ");
        }
        placement = Integer.parseInt(option);
        setPlacementOption(placement);
    }

    private void setupPlayers() {
        choosePlacementOption(this);
        players[0] = new UserPlayer();
        if (getGameMode() == 1) {
            players[1] = new UserPlayer();
        } else {
            if (getDifficulty() == 1) players[1] = new ComputerPlayerEasy();
            else if (getDifficulty() == 2) players[1] = new ComputerPlayerNormal();
            else players[1] = new ComputerPlayerHard();
        }
    }

}
