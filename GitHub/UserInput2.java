package org.deeplearning4j.examples.feedforward.classification.detectgender.GitHub;

import org.nd4j.linalg.api.ops.impl.controlflow.compat.Enter;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

import java.util.ArrayList;

public class UserInput2 extends JFrame implements KeyListener {

    private static final long serialVersionUID = 1L;

    JLabel label;
    JTextField txtOne;
    JTextField txtPassword;

    ArrayList<Integer> keyPresses;
    ArrayList<String> keyPressesStrings;
    long timePressed;
    long timeReleased;

    ArrayList<Long> totalTimePressedList;
    ArrayList<Long> timePressedList;
    ArrayList<Long> timeBetweenPressesList;

    Biometrics biometrics;

    public UserInput2(String s) {
        super(s);
        JPanel p = new JPanel();
        label = new JLabel("Type a 5-character password and press enter when you're done: ");
        p.add(label);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(p);
        addKeyListener(this);
        setSize(400, 400);
        setLocationRelativeTo(null);


        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        keyPresses = new ArrayList<Integer>();
        keyPressesStrings = new ArrayList<String>();
        totalTimePressedList = new ArrayList<Long>();
        timePressedList = new ArrayList<Long>();
        timeBetweenPressesList = new ArrayList<Long>();

        setVisible(true);
    }

    public Biometrics end() {
        timeBetweenPressesList = getTimeBetweenPresses(timePressedList);
        biometrics = new Biometrics(keyPresses, keyPressesStrings, totalTimePressedList, timeBetweenPressesList);
//        System.out.print(biometrics.toString());
        return biometrics;
    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("A key TYPED");
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key typed");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key typed");
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        keyPresses.add(key);
        timePressed = System.nanoTime();
        timePressedList.add(timePressed);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        timeReleased = System.nanoTime();
        totalTimePressedList.add(timeReleased - timePressed);

        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.print("a");
            keyPressesStrings.add("a");
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            System.out.print("b");
            keyPressesStrings.add("b");
        }
        if (e.getKeyCode() == KeyEvent.VK_C) {
            System.out.print("c");
            keyPressesStrings.add("c");
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            System.out.print("d");
            keyPressesStrings.add("d");
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
            System.out.print("e");
            keyPressesStrings.add("e");
        }
        if (e.getKeyCode() == KeyEvent.VK_F) {
            System.out.print("f");
            keyPressesStrings.add("f");
        }
        if (e.getKeyCode() == KeyEvent.VK_G) {
            System.out.print("g");
            keyPressesStrings.add("g");
        }
        if (e.getKeyCode() == KeyEvent.VK_H) {
            System.out.print("h");
            keyPressesStrings.add("h");
        }
        if (e.getKeyCode() == KeyEvent.VK_I) {
            System.out.print("i");
            keyPressesStrings.add("i");
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            System.out.print("j");
            keyPressesStrings.add("j");
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            System.out.print("k");
            keyPressesStrings.add("k");
        }
        if (e.getKeyCode() == KeyEvent.VK_L) {
            System.out.print("l");
            keyPressesStrings.add("l");
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            System.out.print("m");
            keyPressesStrings.add("m");
        }
        if (e.getKeyCode() == KeyEvent.VK_N) {
            System.out.print("n");
            keyPressesStrings.add("n");
        }
        if (e.getKeyCode() == KeyEvent.VK_O) {
            System.out.print("o");
            keyPressesStrings.add("o");
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            System.out.print("p");
            keyPressesStrings.add("p");
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.out.print("q");
            keyPressesStrings.add("q");
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            System.out.print("r");
            keyPressesStrings.add("r");
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            System.out.print("s");
            keyPressesStrings.add("s");
        }
        if (e.getKeyCode() == KeyEvent.VK_T) {
            System.out.print("t");
            keyPressesStrings.add("t");
        }
        if (e.getKeyCode() == KeyEvent.VK_U) {
            System.out.print("u");
            keyPressesStrings.add("u");
        }
        if (e.getKeyCode() == KeyEvent.VK_V) {
            System.out.print("v");
            keyPressesStrings.add("v");
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            System.out.print("w");
            keyPressesStrings.add("w");
        }
        if (e.getKeyCode() == KeyEvent.VK_X) {
            System.out.print("x");
            keyPressesStrings.add("x");
        }
        if (e.getKeyCode() == KeyEvent.VK_Y) {
            System.out.print("y");
            keyPressesStrings.add("y");
        }
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            System.out.print("z");
            keyPressesStrings.add("z");
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            end();
        }
    }

    //This is returning the same elements twice
    public ArrayList<Long> getTimeBetweenPresses(ArrayList<Long> timePressedList) {
        for (int i = 1; i < timePressedList.size(); i++) {
            timeBetweenPressesList.add(timePressedList.get(i) - timePressedList.get(i - 1));
        }
        return timeBetweenPressesList;
    }
}
