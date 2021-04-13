package com.company;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Random random1 = new Random();
        Scanner scanner = new Scanner(System.in);
        double archersForce = 100;
        double wizardsForce = 100;
        double archersLife;
        double wizardsLife;

        File file = new File("doc.txt");
        if(getCounterFromDoc()==0) {
            archersLife = 1500 + random1.nextInt(300);
            wizardsLife = 1500 + random1.nextInt(300);
        }
        else{
            archersLife=getALifeFromDoc();
            wizardsLife=getWLifeFromDoc();
        }
        Archer archer1 = new Archer("Archer1", archersLife, 5.0, archersForce);
        Wizzard wizzard1 = new Wizzard("Wizzard1", wizardsLife, 15.0, wizardsForce);

        saveDataInFile(archer1.getLife(), wizzard1.getLife(), 1);



        while (true) {
            System.out.println("Input enter for playing game");
            System.out.println("Or something for exit and deleting data in file");
            String input = scanner.nextLine();
            if (input == "") {
                playingGame(archer1, wizzard1);
            }
            else {
                saveDataInFile(0,0,0);
                System.out.println("Deleting data in file");
                break;
            }
        }
    }


    public static void playingGame(Archer archer, Wizzard wizzard) throws IOException {
        double aLife, wLife;
        int counter;

        aLife = getALifeFromDoc();
        wLife = getWLifeFromDoc();
        counter = getCounterFromDoc();

        archer.setLife(aLife);
        wizzard.setLife(wLife);


        if (counter == 1) {

            archer.beat(wizzard);
            wLife = (wLife - ((archer.getForce()) * (100 - wizzard.getArmor()) / 100));
            wizzard.setLife(wLife);

            if (wLife > 0) {
                counter = 2;
            } else {
                System.out.println("Game over.");
                System.out.println(archer.getName() + "win!!!");
                counter = 3;
            }


            saveDataInFile(aLife, wLife, counter);
        } else if (counter == 2) {

            wizzard.beat(archer);
            aLife = (aLife - ((wizzard.getForce()) * (100 - archer.getArmor()) / 100));
            archer.setLife(aLife);
            if (aLife > 0) {
                counter = 1;
            } else {
                System.out.println("Game over.");
                System.out.println(wizzard.getName() + "win!!!");
                counter = 4;
            }
            saveDataInFile(aLife, wLife, counter);
        } else if (counter == 3) {
            System.out.println("Game over.");
            System.out.println(archer.getName() + "win!!!!!!");
        } else if (counter == 4) {
            System.out.println("Game over.");
            System.out.println(wizzard.getName() + "win!!!!!!");
        }


    }

    public static void saveDataInFile(double a, double b, int c) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("doc.txt"));
        writer.write("Archers life : " + a);
        writer.newLine();
        writer.write("Wizzards life : " + b);
        writer.newLine();
        writer.write("Counter : " + c);

        writer.close();
    }

    public static double getWLifeFromDoc() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("doc.txt"));
        reader.readLine();
        String wizzardsLine = reader.readLine();
        reader.close();

        String wariorsSubstr = wizzardsLine.substring(15);
        double wizzardsResult = Double.parseDouble(wariorsSubstr);
        return wizzardsResult;

    }

    public static double getALifeFromDoc() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("doc.txt"));
        String archersLine = reader.readLine();
        reader.close();

        String archersSubstr = archersLine.substring(15);
        double archersResult = Double.parseDouble(archersSubstr);
        return archersResult;
    }


    public static int getCounterFromDoc() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("doc.txt"));
        reader.readLine();
        reader.readLine();
        String counterLine = reader.readLine();
        reader.close();

        String counterSubstr = counterLine.substring(10);
        int counterResult = Integer.parseInt(counterSubstr);
        return counterResult;
    }
}
