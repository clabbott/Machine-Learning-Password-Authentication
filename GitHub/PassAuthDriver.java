package org.deeplearning4j.examples.feedforward.classification.detectgender.GitHub;

public class PassAuthDriver  {

    public static void main(String[] args){
        //Get user-entered username

        //Compare username to existing usernames, determines user
        //May need to create user class to store user data or just pass username as a string

        //Get user-entered password while taking time key were pressed down
        //Store time press of values in an array/ArrayList
        //Will always only be 5 key presses
        //Somehow convert this to a value understandable by NN

        //Compares values of user-entered password to password

        //Calls passAuthNN with parameters String username, (Arrsy) Passvalues

        //Fix this. Add second parameter with array of 9 string values
        UserInput.writeDataLineByLine("C:\\Users\\Clark Abbott\\dl4j-examples\\dl4j-examples\\src\\main\\resources" +
            "\\classification\\clarkData.csv");

        //Assign the user above a label according to their username.


        try{
            System.out.println("Running Password Authentication now.");

            //Pass expected label along too
            boolean val = PasswordAuthentication.NeuralNetwork();
            System.out.println("Neural Network returned " + val);
        }catch (Exception e){
            System.out.println("Unexpected Error:" +e);
        }

    }
}
