package com.codecool.battleship.ships;

import com.codecool.battleship.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private List<Square> coords = new ArrayList<Square>();

    public List<Square> getCoords() {
        return coords;
    }

    public void addCords(Square square) {
        coords.add(square);
    }

}
