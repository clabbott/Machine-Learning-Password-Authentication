package org.deeplearning4j.examples.feedforward.classification.detectgender.GitHub;

import java.util.ArrayList;

public class Biometrics {
    ArrayList<Integer> keyPresses;
    ArrayList<String> keyPressesStrings;
    ArrayList<Long> timePressedList;
    ArrayList<Long> timeBetweenPressesList;

    public Biometrics() {}

    public Biometrics(ArrayList<Integer> keyPresses, ArrayList<String> keyPressesStrings, ArrayList<Long> timePressedList, ArrayList<Long> timeBetweenPressesList) {
        this.keyPresses = keyPresses;
        this.keyPressesStrings = keyPressesStrings;
        this.timePressedList = timePressedList;
        this.timeBetweenPressesList = timeBetweenPressesList;
    }

    public String toString() {
        String press1 = new String(keyPressesStrings.get(0) + ": " + timePressedList.get(0));
        String between1 = new String("Time between " + keyPressesStrings.get(0) + " and " + keyPressesStrings.get(1) + ": " + timeBetweenPressesList.get(0));
        String press2 = new String(keyPressesStrings.get(1) + ": " + timePressedList.get(1));
        String between2 = new String("Time between " + keyPressesStrings.get(1) + " and " + keyPressesStrings.get(2) + ": " + timeBetweenPressesList.get(1));
        String press3 = new String(keyPressesStrings.get(2) + ": " + timePressedList.get(2));
        String between3 = new String("Time between " + keyPressesStrings.get(2) + " and " + keyPressesStrings.get(3) + ": " + timeBetweenPressesList.get(2));
        String press4 = new String(keyPressesStrings.get(3) + ": " + timePressedList.get(3));
        String between4 = new String("Time between " + keyPressesStrings.get(3) + " and " + keyPressesStrings.get(4) + ": " + timeBetweenPressesList.get(3));
        String press5 = new String(keyPressesStrings.get(4) + ": " + timePressedList.get(4));
        String result = "\n" +  press1 + "\n" + between1 + "\n" + press2 + "\n" + between2 + "\n" + press3 + "\n" + between3 + "\n" + press4 + "\n" + between4 + "\n" + press5;
        return result;
    }
}
