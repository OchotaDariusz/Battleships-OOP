package com.codecool.battleship.board;

import com.codecool.battleship.io.Input;
import com.codecool.battleship.players.AbstractPlayer;

public interface BoardFactory {

    void randomPlacement(int shipSize);

    void manualPlacement(AbstractPlayer player, Board board, int shipSize, Input input);
}
