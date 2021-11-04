package org.rectangles.ui;

import org.rectangles.entities.Point;
import org.rectangles.entities.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CanvasDraw extends JPanel {

    private boolean startDraw = true;
    public Point startPoint;
    public Point endPoint;
    private Rectangle recA;
    private Rectangle recB;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize(500,500);
        this.setBackground(new Color(30, 50, 50));
        g.setColor(Color.WHITE);
        //g.drawRect(20, 20, 60, 60);
        if(recA != null){
            g.drawRect(recA.getX(), recA.getY(), recA.getWidth(), recA.getHeight());
        }
        if(recB != null){
            g.drawRect(recB.getX(), recB.getY(), recB.getWidth(), recB.getHeight());
        }
        if(startPoint!=null){
            g.setColor(Color.RED);
            g.drawRect(startPoint.getX(), startPoint.getY(), 1, 1);
        }
    }

    public CanvasDraw(){
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registerClick(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void registerClick(MouseEvent e){
        if(startDraw){
            startPoint = new Point(e.getX(), e.getY());
            startDraw = false;
        }else{
            endPoint = new Point(e.getX(), e.getY());
            saveRectangle();
            startDraw = true;
            startPoint = null;
        }
        this.updateUI();
    }

    private void saveRectangle(){
        Rectangle temp = new Rectangle(startPoint.getX(), startPoint.getY(),
                endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY());

        temp = validateAndFix(temp);

        if(recA == null){
            recA = temp;
        } else if(recB == null){
            recB = temp;
        } else {
            recA = recB;
            recB = temp;
        }
        System.out.println(temp.toString());
    }

    private Rectangle validateAndFix(Rectangle r){
        if(r.getHeight() < 0){
            r.setY( r.getY() + r.getHeight());
            r.setHeight( r.getHeight() * -1 );
        }
        if(r.getWidth() < 0){
            r.setX( r.getX() + r.getWidth());
            r.setWidth( r.getWidth() * -1 );
        }
        return r;
    }
}