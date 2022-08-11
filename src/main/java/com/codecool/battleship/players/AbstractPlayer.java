package com.codecool.battleship.players;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.Square;
import com.codecool.battleship.board.SquareStatus;
import com.codecool.battleship.ships.Ship;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer {

    private final List<Ship> ships = new ArrayList<Ship>();

    public List<Ship> getShips() {
        return ships;
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public boolean isAlive() {
        return ships.size() != 0;
    }

    public void setSquareToHit(int[] cords, Square[][] ocean, Square[][] emptyOcean, AbstractPlayer player) {
        List<Ship> ships = getShips();

        for (Ship ship : ships) {
            List<Square> shipCoords = ship.getCoords();
            for (Square square : shipCoords) {
                if (cords[0] == square.getX() && cords[1] == square.getY()) {
                    square.setSquareStatus(SquareStatus.S_HIT);
                }
            }
            if (isShipSunked(ship)) {
                removeShip(ship, ocean, emptyOcean, player);
                if (this instanceof ComputerPlayerHard) {
                    ((AbstractComputerPlayer)this).clearHittedFields();
                }
                break;
            }
        }
    }

    public boolean isShipSunked(Ship ship) {
        for (Square square : ship.getCoords()) {
            if (square.getSquareStatus() != SquareStatus.S_HIT)
                return false;
        }
        return true;
    }

    public void removeShip(Ship ship, Square[][] ocean, Square[][] emptyOcean, AbstractPlayer player) {
        for (Square square : ship.getCoords()) {
            ocean[square.getX()][square.getY()].setSquareStatus(SquareStatus.S_SUNK);
            emptyOcean[square.getX()][square.getY()].setSquareStatus(SquareStatus.S_SUNK);
            if (player instanceof AbstractComputerPlayer) {
                ((AbstractComputerPlayer)player).addSunkenShipsFields(new int[]{square.getX(), square.getY()});
                if (player instanceof ComputerPlayerNormal) {
                    ((AbstractComputerPlayer)player).addToUsedFieldsAfterSunk();
                }
            }
        }
        ships.remove(ship);
    }

    @Override
    public String toString() {
        return "AbstractPlayer{" +
                "ships=" + ships +
                '}';
    }

    public boolean makeMove(Board opponentBord, Board emptyBoard, int[] coords, AbstractPlayer opponent) {
        Square[][] ocean = opponentBord.getOcean();
        Square[][] empty = emptyBoard.getOcean();

        try {
            if (ocean[coords[0]][coords[1]].getSquareStatus() == SquareStatus.S_SHIP) {
                empty[coords[0]][coords[1]].setSquareStatus(SquareStatus.S_HIT);
                ocean[coords[0]][coords[1]].setSquareStatus(SquareStatus.S_HIT);
                opponent.setSquareToHit(coords, empty, ocean, this);
                return true;

            } else if (ocean[coords[0]][coords[1]].getSquareStatus() == SquareStatus.S_EMPTY) {
                empty[coords[0]][coords[1]].setSquareStatus(SquareStatus.S_MISS);
                ocean[coords[0]][coords[1]].setSquareStatus(SquareStatus.S_MISS);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return false;
    }
}