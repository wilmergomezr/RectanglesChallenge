package org.rectangles.ui;

import org.rectangles.entities.Point;
import org.rectangles.entities.Rectangle;
import org.rectangles.operations.RectangleAdjacency;
import org.rectangles.operations.RectangleContainment;
import org.rectangles.operations.RectangleIntersection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Optional;

public class CanvasDraw extends JPanel {

    private boolean startDraw = true;
    public Point startPoint;
    public Point endPoint;
    private Rectangle recA;
    private Rectangle recB;
    private List<Point> drawPoints;
    private TextArea results;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize(500,500);
        this.setBackground(new Color(30, 50, 50));
        g.setColor(Color.WHITE);
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

        if(drawPoints!= null){
            g.setColor(Color.BLUE);
            for (Point point : drawPoints){
                g.fillOval(point.getX()-3, point.getY()-3, 6, 6);
            }
        }
    }

    public CanvasDraw(TextArea results){
        this.results = results;
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                registerClick(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {            }

            @Override
            public void mouseEntered(MouseEvent e) {            }

            @Override
            public void mouseExited(MouseEvent e) {            }
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
            updateResults(recA, recB);
        } else {
            recA = recB;
            recB = temp;
            updateResults(recA, recB);
        }

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

    private void updateResults(Rectangle recA, Rectangle recB){
        RectangleIntersection intersection = new RectangleIntersection();

        Optional<List<Point>> points =  intersection.getInterceptionPoints(recA, recB);
        results.setText("Intersection Validation Result: " + intersection.isIntersection(points) + "\n");
        if(!points.isEmpty()) {
            for (Point p : points.get()){
                results.setText(results.getText() + "Intersection point "+ p.toString() + "\n");
            }
        }
        results.setText(results.getText() + "\n" + "Containment Validation Result: " + (new RectangleContainment()).validate(recA, recB) + "\n");
        results.setText(results.getText() + "Adjacency Validation Result: " + (new RectangleAdjacency()).validate(recA, recB) + "\n");

        if(!points.isEmpty()) {
            drawPoints = points.get();
        }else{
            drawPoints = null;
        }
    }
}
