package org.rectangles.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JWindow {

    private JLabel pointA;
    private JLabel pointB;
    private CanvasDraw drawPanel;

    public MainWindow(){
        this.setSize(600, 600);
        //this.setBounds(20,20,400,400);
        this.setLayout(new GridLayout(2, 3));

        pointA = new JLabel("Start point: ");
        pointB = new JLabel("End point: ");
/*
        this.getContentPane().add(pointA);
        this.getContentPane().add(pointB);
*/
        drawPanel = new CanvasDraw();

        this.getContentPane().add(drawPanel);

        this.setVisible(true);
    }

    public static void main(String args[]){
        MainWindow myWindow = new MainWindow();
    }

}
