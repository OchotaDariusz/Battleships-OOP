package com.codecool.battleship.players;

import com.codecool.battleship.ships.Ship;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer {

    private final List<Ship> ships = new ArrayList<Ship>();

    public List<Ship> getShips() {
        return ships;
    }

    public boolean isAlive() {
        return ships.size() != 0;
    }

    abstract void makeMove();

}
