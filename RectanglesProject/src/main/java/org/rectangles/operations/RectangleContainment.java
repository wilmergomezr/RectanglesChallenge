package org.rectangles.operations;

import org.rectangles.entities.Line;
import org.rectangles.entities.Rectangle;
import org.rectangles.utils.ValidationResult;


public class RectangleContainment extends RectangleValidationAlgorithm{

    @Override
    public String validate(Rectangle a, Rectangle b) {

        if(isContainmentRectangle(a, b)){
            return ValidationResult.CONTAINMENT.getValue();
        }

        return ValidationResult.NO_CONTAINMENT.getValue();
    }

    private boolean isContainmentRectangle(Rectangle r1, Rectangle r2){

        Line rectangleDiagonal = new Line(r2.getX(), r2.getY(), r2.getX() + r2.getWidth(), r2.getY() + r2.getHeight());
        if(isLineInside(r1, rectangleDiagonal)){
            System.out.println("Is inside: " + rectangleDiagonal.toString());
            return true;
        }

        rectangleDiagonal = new Line(r1.getX(), r1.getY(), r1.getX() + r1.getWidth(), r1.getY() + r1.getHeight());
        if(isLineInside(r2, rectangleDiagonal)){
            System.out.println("Is inside: " + rectangleDiagonal.toString());
            return true;
        }

        return false;
    }



}
