package com.codecool.battleship.ships;

public enum ShipType {

    CARRIER(1),
    CRUISER(2),
    BATTLESHIP(3),
    DESTROYER(4);
    private final int shipLength;

    ShipType(int shipLength) {
        this.shipLength = shipLength;
    }


}
