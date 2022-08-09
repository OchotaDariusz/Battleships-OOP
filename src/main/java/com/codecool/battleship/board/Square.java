package com.codecool.battleship.board;

public class Square {
    private int X;
    private int Y;
    private Status SquareStatus;

    public void setSquareStatus(Status squareStatus) {
        SquareStatus = squareStatus;
    }

    public enum Status {
        S_EMPTY,
        S_SHIP,
        S_HIT,
        S_MISS;

        public char GetCharacter() {
            if (this == Status.S_SHIP) {
                return '*';
            }
            if (this == Status.S_HIT) {
                return 'x';
            }
            if (this == Status.S_MISS) {
                return 'o';
            }
            return ' ';
        }
    }

    public Status getSquareStatus() {
        return SquareStatus;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Square(int x, int y, Status status) {
        X = x;
        Y = y;
        SquareStatus = status;
    }

    @Override
    public String toString() {
        return Character.toString(this.SquareStatus.GetCharacter());
    }
}
