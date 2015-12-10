package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*

        *
        * Sloppy code. I know. It's a proof of concept for the Gameshow Probability Question.
        *

         */
        randomizeDoors();
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter iterations: ");

        int iterations = scan.nextInt();

        boolean[] switchResults = new boolean[iterations];
        boolean[] remainResults = new boolean[iterations];

        System.out.println("Running remain simulation...");

        for(int i = 0; i < iterations; i++){
            remainResults[i] = didPickRightDoorNoSwitch();
            randomizeDoors();
        }

        System.out.println("Running switch simulation...");

        for(int i = 0; i < iterations; i++){
            switchResults[i] = didPickRightDoorSwitch();
            randomizeDoors();
        }

        System.out.println("Processing results...");

        int remainCorrect = 0;
        int switchCorrect = 0;

        for(boolean b : switchResults){
            if(b){
                switchCorrect++;
            }
        }

        for(boolean b : remainResults){
            if(b){
                remainCorrect++;
            }
        }

        System.out.println("SWITCH PERCENTAGE: " + ((switchCorrect / (iterations + 0.0))*100) + "%; REMAIN PERCENTAGE: " + ((remainCorrect / (iterations + 0.0))*100) + "%");

        /*for(boolean b : switchResults){
                System.out.println("Switch: " + b);

        }

        for(boolean b : remainResults){
                System.out.println("Remain: " + b);

        }*/

    }

    public static boolean door[] = {false, false, false};

    public static void randomizeDoors(){
        for(int i = 0; i < door.length; i++){ // reset all to false
            door[i] = false;
        }
        Random rand = new Random();

        int prizeDoor = rand.nextInt(door.length);

        door[prizeDoor] = true;
    }

    public static boolean didPickRightDoorSwitch(){
        Random rand = new Random();
        int firstPick = rand.nextInt(door.length);
        int falseDoor = getFalse(firstPick);
        int[] picks = {firstPick, falseDoor};
        int finalPick = getOtherIndex(picks, 3);
        //System.out.println("Picked: " + door[finalPick]);
        return door[finalPick];
    }

    /*public static int getOther(boolean picks[], int picked[]){
        for(int i = 0; i < picks.length; i++){
            boolean alreadyPicked = false;
            for(int j = 0; j < picked.length; j++){
                if(i == j){
                    alreadyPicked = true;
                }
            }
            if(!alreadyPicked){
                return i;
            }
        }
        Thread.dumpStack();
        return 0;
    }*/

    public static boolean contains(int[] container, int check){
        for(int i : container){
            if(check == i){
                return true;
            }
        }
        return false;
    }

    public static int getOtherIndex(int[] picked, int indexes){
        for(int i = 0; i < indexes; i++){
            if(contains(picked, i)){
                continue;
            }
            return i;
        }
        return 0;
    }

    public static int getFalse(int picked){
        for(int i = 0; i < door.length; i++){
            if(i == picked){
                continue;
            }
            if(!door[i]){
                return i;
            }
        }
        Thread.dumpStack();
        return 0; // should never get to this point
    }

    public static boolean didPickRightDoorNoSwitch(){
        Random rand = new Random();
        boolean pick = door[rand.nextInt(door.length)];
        //System.out.println("Picked: " + pick);
        return pick;
    }
}
