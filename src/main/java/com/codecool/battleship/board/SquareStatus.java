package com.codecool.battleship.board;

public enum SquareStatus {

    S_EMPTY,
    S_SHIP,
    S_HIT,
    S_MISS;

    public char getCharacter() {
        if (this == S_SHIP) {
            return '*';
        }
        if (this == S_HIT) {
            return 'x';
        }
        if (this == S_MISS) {
            return 'o';
        }
        return ' ';
    }

}
