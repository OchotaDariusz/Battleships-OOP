package com.codecool.battleship.board;

import com.codecool.battleship.io.Input;

public interface BoardFactory {


    void randomPlacement(int shipSize);

    void manualPlacement(Board board, int shipSize, Input input);
}
