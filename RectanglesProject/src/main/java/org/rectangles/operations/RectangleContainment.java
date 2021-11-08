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

    private boolean isContainmentRectangle(Rectangle rectangle1, Rectangle rectangle2){

        Line rectangleDiagonal = new Line(rectangle2.getX(), rectangle2.getY(), rectangle2.getX() + rectangle2.getWidth(), rectangle2.getY() + rectangle2.getHeight());
        if(isLineInside(rectangle1, rectangleDiagonal)){
            System.out.println("Is inside: " + rectangleDiagonal.toString());
            return true;
        }

        rectangleDiagonal = new Line(rectangle1.getX(), rectangle1.getY(), rectangle1.getX() + rectangle1.getWidth(), rectangle1.getY() + rectangle1.getHeight());
        if(isLineInside(rectangle2, rectangleDiagonal)){
            System.out.println("Is inside: " + rectangleDiagonal.toString());
            return true;
        }

        return false;
    }



}
