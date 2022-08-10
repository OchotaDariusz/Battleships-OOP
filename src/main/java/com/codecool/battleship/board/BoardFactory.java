package com.codecool.battleship.board;

import com.codecool.battleship.io.Input;
import com.codecool.battleship.players.AbstractPlayer;

public interface BoardFactory {


    void randomPlacement(AbstractPlayer player, Board board, int shipSize, Input input);

    void manualPlacement(AbstractPlayer player, Board board, int shipSize, Input input);
}
