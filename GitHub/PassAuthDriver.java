package org.deeplearning4j.examples.feedforward.classification.detectgender.GitHub;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PassAuthDriver  {

    public static void main(String[] args){
        //To do:
        // find a way to get data from userinput2 into clarkData.csv
        // relay that csv to the NN
        // delete userinput, rename userinput2
        // make it easy to export and redo on pi for mike
        // put code on github

        UserInput2 test = new UserInput2("Key Listener Tester");

        try{
            Thread.sleep(10000);
        }catch(Exception e){System.out.println("error");}

        Biometrics data = test.end();

        if(!data.keyPressesStrings.toString().equals("[c, l, a, r, k]")){
            System.out.println("");
            System.out.println("Incorrect password.");
            return;
        }else {
            System.out.println("");
            System.out.println("Correct password! Time for step 2.");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("error");
            }
        }

        ArrayList<Long> placeholder = new ArrayList<>();
        placeholder.add((long) 0);

        for (Long elem:data.timePressedList){
            placeholder.add(elem);
        }
        for (Long elem:data.timeBetweenPressesList.subList(0,5)){
            placeholder.add(elem);
        }
        System.out.println(placeholder);

        String[] arr = new String[placeholder.size()];
        for(int i=0;i<arr.length;i++){
            arr[i] = placeholder.get(i).toString();
            System.out.println(placeholder.get(i));
        }

        File file = new File("C:\\Users\\Clark Abbott\\dl4j-examples\\dl4j-examples\\src\\main\\resources" +
            "\\classification\\passData.csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // add data to csv
            //Make this take in data from the passed parameter insted of just using this
            writer.writeNext(arr);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("You done fucked up now");
        }

        try{
            System.out.println("Running Password Authentication now.");

            boolean val = PasswordAuthentication.NeuralNetwork();
            System.out.println("Neural Network returned " + val);
        }catch (Exception e){
            System.out.println("Unexpected Error:" +e);
        }
    }
}
