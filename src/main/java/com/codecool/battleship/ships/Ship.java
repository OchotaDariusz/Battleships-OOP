package com.codecool.battleship.ships;

import com.codecool.battleship.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private List<Square> coords = new ArrayList<Square>();

    public List<Square> getCoords() {
        return coords;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "coords=" + coords +
                '}';
    }
    public int getShipSize(){
        return coords.size();
    }

    public void addCords(Square square) {
        coords.add(square);
    }

}
