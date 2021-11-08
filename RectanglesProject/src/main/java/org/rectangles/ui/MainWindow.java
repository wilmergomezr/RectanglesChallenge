package org.rectangles.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

    private CanvasDraw drawPanel;

    public MainWindow(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        this.setSize(500, 800);
        this.setLayout(new BorderLayout(3,3));


        TextArea area = new TextArea();
        area.setSize(500, 300);
        area.setText("Validations");
        drawPanel = new CanvasDraw(area);


        this.getContentPane().add(drawPanel, BorderLayout.NORTH);
        this.getContentPane().add(area, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public static void main(String args[]){
        MainWindow myWindow = new MainWindow();
    }

}
