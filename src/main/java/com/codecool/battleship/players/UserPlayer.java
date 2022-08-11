package com.codecool.battleship.players;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.Square;
import com.codecool.battleship.board.SquareStatus;

public class UserPlayer extends AbstractPlayer {



    @Override
    public void makeMove(Board opponentBord, Board emptyBoard, int[] coords, AbstractPlayer opponent) {
        Square ocean [][] =opponentBord.getOcean();
        Square empty [][] = emptyBoard.getOcean();

        if(ocean[coords[0]][coords[1]].getSquareStatus()== SquareStatus.S_SHIP){
            empty[coords[0]][coords[1]].setSquareStatus(SquareStatus.S_HIT);
            opponent.setSquareToHit(coords);


        } else if (ocean[coords[0]][coords[1]].getSquareStatus()== SquareStatus.S_EMPTY) {
            empty[coords[0]][coords[1]].setSquareStatus(SquareStatus.S_MISS);
        }
//      check if lose;
//wez liste statków oponenta i zmien statku po trafieniu
//        jesli cała lista jest zatopiona to loose

//        wez cordy --- funkcja je dostanie?
//        wez tablice przeciwnika
//        sprawdz co jest na tablicy tam gdze strzeliłem(czyli czek if hit)
//        oznacz na mojej empty tablicy jaki to był strzał( miss / hit / sunk)

        
    }

    @Override
    public void makeMove() {

    }
}
