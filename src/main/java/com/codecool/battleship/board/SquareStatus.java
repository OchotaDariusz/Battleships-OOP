package com.codecool.battleship.board;

public enum SquareStatus {

    S_EMPTY,
    S_SHIP,
    S_HIT,
    S_MISS,
    S_SUNK;
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
        if(this==S_SUNK){
            return 'S';
        }
        return ' ';
    }

}
