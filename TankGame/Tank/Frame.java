package Tank;

import beingInherited.Panel;
import keys.Controller;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame(Panel objPanel){
        setTitle("Tank Game");
        add(objPanel);
        pack();//sizes the frame so all contents are at or above preferred sizes
        Thread th = new Thread(objPanel); //thread
        th.start(); //start thread
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit on close
        setResizable(false); //cannot resize frame
        setVisible(true);
    }

    public static void main(String [] args){
        new Frame(new Controller()); //call controller
    }
}
