package org.rectangles.operations;

import org.rectangles.entities.Line;
import org.rectangles.entities.Point;
import org.rectangles.entities.Rectangle;

import java.util.ArrayList;
import java.util.List;

public abstract class RectangleValidationAlgorithm {

    public abstract String validate(Rectangle a, Rectangle b);

    public List<Line> breakRectangleHorizontal(Rectangle r){
        List<Line> lines = new ArrayList<>();

        lines.add(new Line(r.getX(), r.getY(), r.getX() + r.getWidth(), r.getY()));
        lines.add(new Line(r.getX() , r.getY() + r.getHeight(), r.getX()+r.getWidth(), r.getY() + r.getHeight()));

        return lines;
    }

    public List<Line> breakRectangleVertical(Rectangle r){
        List<Line> lines = new ArrayList<>();

        lines.add(new Line(r.getX(), r.getY(), r.getX() , r.getY() + r.getHeight()));
        lines.add(new Line(r.getX() + r.getWidth(), r.getY(), r.getX() +r.getWidth(), r.getY() + r.getHeight()));

        return lines;
    }

    protected boolean isPointInside(Rectangle r, Point p){
        if(p.getX() > r.getX() && p.getX() < r.getX() + r.getWidth()){
            if(p.getY() > r.getY() && p.getY() < r.getY() + r.getHeight()){
                return true;
            }
        }
        return false;
    }

    protected boolean isPointYInsideOrTouching(Line l, Point p){
        if(p.getY() >= l.getStartPoint().getY() && p.getY() <= l.getEndPoint().getY()){
            return true;
        }
        return false;
    }

    public boolean isLineInside(Rectangle r, Line line){

        return isPointInside(r, line.getStartPoint()) && isPointInside(r, line.getEndPoint());

    }

}
