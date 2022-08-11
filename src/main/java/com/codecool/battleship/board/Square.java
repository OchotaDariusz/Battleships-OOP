package com.codecool.battleship.board;

public class Square {
    private final int x;
    private final int y;
    private SquareStatus squareStatus;

    public void setSquareStatus(SquareStatus squareStatus) {
        this.squareStatus = squareStatus;

    }

    public SquareStatus getSquareStatus() {
        return squareStatus;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Square(int x, int y, SquareStatus status) {
        this.x = x;
        this.y = y;
        squareStatus = status;
    }

    @Override
    public String toString() {
        return Character.toString(this.squareStatus.getCharacter());
    }
}
