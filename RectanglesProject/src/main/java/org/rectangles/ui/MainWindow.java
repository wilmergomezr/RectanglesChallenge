package org.rectangles.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JWindow {

    private CanvasDraw drawPanel;

    public MainWindow(){
        this.setSize(600, 600);
        this.setLayout(new GridLayout(2, 3));

        drawPanel = new CanvasDraw();

        this.getContentPane().add(drawPanel);

        this.setVisible(true);
    }

    public static void main(String args[]){
        MainWindow myWindow = new MainWindow();
    }

}
