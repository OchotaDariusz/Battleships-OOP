package com.codecool;

import com.codecool.battleship.*;
import com.codecool.battleship.board.Board;

public class Main {

    public static void main(String[] args) {

        Board b = new Board();
        System.out.println("New Ship");
        System.out.println(b.shipPlace(0,0));
        System.out.println("\n\n");

        System.out.println("New Ship");
        System.out.println(b.shipPlace(1,7));
        System.out.println("\n\n");

        System.out.println("New Ship");
        System.out.println(b.shipPlace(5,5));
        System.out.println("\n\n");



        System.out.println(b.toString());

        //Battleship game = new Battleship();
        //game.startGame();
    }
}