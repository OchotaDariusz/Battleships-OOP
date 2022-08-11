package com.codecool.battleship.players;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.Square;
import com.codecool.battleship.board.SquareStatus;
import com.codecool.battleship.ships.Ship;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer {

    private List<Ship> ships = new ArrayList<Ship>();


    public List<Ship> getShips() {
        return ships;
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public boolean isAlive() {
        return ships.size() != 0;
    }

    public void setSquareToHit(int[] cords) {
        List<Ship> ships = getShips();

        for (Ship ship : ships) {
            List<Square> shipCoords = ship.getCoords();

            for (Square square : shipCoords) {

                if (cords[0] == square.getX() && cords[1] == square.getY()) {
                    square.setSquareStatus(SquareStatus.S_HIT);

                }
            }
            if (isShipSunked(ship)) {
                removeShip(ship);
                System.out.println("zatonął");
//
                break;
            }
        }
    }

    public boolean isShipSunked(Ship ship) {
        for (Square sqare : ship.getCoords()) {
            if (sqare.getSquareStatus() != SquareStatus.S_HIT)
                return false;
        }
        return true;
    }

    public void removeShip(Ship ship) {
        ships.remove(ship);
    }


    @Override
    public String toString() {
        return "AbstractPlayer{" +
                "ships=" + ships +
                '}';
    }

    public abstract void makeMove(Board opponentBord, Board emptyBoard, int[] coords, AbstractPlayer opponent);

    abstract void makeMove();

}