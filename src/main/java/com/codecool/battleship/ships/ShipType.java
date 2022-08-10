package com.codecool.battleship.ships;

public enum ShipType {
    CARRIER(1),
    CRUISER(2),
    BATTLESHIP(3),
    SUBMARINE(4),
    DESTROYER(5);
    private final int shipLength;

    public int getShipLength() {
        return shipLength;
    }

    ShipType(int shipLength) {
        this.shipLength = shipLength;
    }
}
