package com.codecool;

import com.codecool.battleship.*;

public class Main {

    public static void main(String[] args) {

        Board b = new Board();
        System.out.println("New Ship");
        System.out.println(b.shipPlace(1,1));
        System.out.println(b.shipCommit());
        System.out.println("\n\n");

        System.out.println("New Ship");
        System.out.println(b.shipPlace(1,1));
        System.out.println(b.shipPlace(1,2));
        System.out.println(b.shipCommit());
        System.out.println("\n\n");

        System.out.println("New Ship");
        System.out.println(b.shipPlace(5,5));
        System.out.println(b.shipPlace(6,5));
        System.out.println(b.shipCommit());
        System.out.println("\n\n");

        Battleship game = new Battleship();
        game.startGame();
    }
}