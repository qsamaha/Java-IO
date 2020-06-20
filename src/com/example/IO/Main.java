package com.example.IO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Locations locations = new Locations();

    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);

        int loc = 1;
        while(true){
            System.out.println(locations.get(loc).getDescription());
            if(loc == 0){
                break;
            }

            Map <String, Integer> exits = locations.get(loc).getExits();
            System.out.println("Available exits are ");
            for(String exit: exits.keySet()){
                System.out.print(exit + ", ");
            }
            System.out.println();

            String east = "east";
            String west = "west";
            String south = "south";
            String north = "north";
            String quit = "quit";
            String direction = scan.nextLine().toUpperCase();

            if(direction.contains(east.toUpperCase())){
                direction = "E";
            }else if(direction.contains(west.toUpperCase())){
                direction = "W";
            }else if(direction.contains(south.toUpperCase())){
                direction = "S";
            }else if(direction.contains(north.toUpperCase())){
                direction = "N";
            }else if(direction.contains(quit.toUpperCase())){
                direction = "Q";
            }

            if(exits.containsKey(direction)){
                loc = exits.get(direction);
            }else{
                System.out.println("You cannot go in that direction");
            }
        }

    }
}
