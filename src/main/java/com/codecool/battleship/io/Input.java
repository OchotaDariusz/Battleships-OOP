package com.codecool.battleship.io;

import java.util.Scanner;

public class Input {

    public boolean validateForBoardSize(int size) {
        return size < 10 || size > 20;
    }

    public String askForInput(String label) {
        System.out.println(label);
        Scanner coordinates = new Scanner(System.in);
        return coordinates.nextLine().toUpperCase();
    }

    public boolean validateOption(String input) {
        return input.equals("1") || input.equals("2");
    }



    public boolean validateCoords(String input, int size) {
        try {
            int[] coords = convertCoords(input);
            return ((coords[0] <= size - 1 && coords[0] >= 0) &&
                    (coords[1] <= size - 1 && coords[1] >= 0));
        } catch (Exception e) {
            return false;
        }
    }

    private int[] convertCoords(String input) {
        int column = input.charAt(0) - 'A';
        int row = Integer.parseInt(input.substring(1)) - 1;
        int[] coords = new int[2];
        coords[0] = column;
        coords[1] = row;
        return coords;
    }

    public int[] askForCords(String label,int size){
        String input = askForInput(label);
        while(!validateCoords(input,size)){
            input = askForInput(label);
        }

        return convertCoords(input);
    }



}

