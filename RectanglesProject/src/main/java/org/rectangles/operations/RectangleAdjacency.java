package org.rectangles.operations;

import org.rectangles.entities.Line;
import org.rectangles.entities.Rectangle;
import org.rectangles.utils.ValidationResult;

public class RectangleAdjacency extends RectangleValidationAlgorithm{

    @Override
    public String validate(Rectangle a, Rectangle b) {

        if(a.getX() < b.getX() ){
            Line faceRectangleA = new Line(a.getX() + a.getWidth(), a.getY(), a.getX() + a.getWidth(), a.getY() + a.getHeight());
            Line faceRectangleB = new Line(b.getX(), b.getY(), b.getX(), b.getY() + b.getHeight());
            return areAdjacent(faceRectangleA, faceRectangleB);
        }
        Line faceRectangleA = new Line(a.getX(), a.getY(), a.getX(), a.getY() + a.getHeight());
        Line faceRectangleB = new Line(b.getX() + b.getWidth(), b.getY(), b.getX() + b.getWidth(), b.getY() + b.getHeight());

        return areAdjacent(faceRectangleB, faceRectangleA);

    }

    private String areAdjacent(Line leftRectangleFace, Line rightRectangleFace){
        if(leftRectangleFace.getStartPoint().getX() == rightRectangleFace.getStartPoint().getX()){
            if(leftRectangleFace.getStartPoint().getY() == rightRectangleFace.getStartPoint().getY() &&
                    leftRectangleFace.getEndPoint().getY() == rightRectangleFace.getEndPoint().getY()){
                return ValidationResult.ADJACENT_PROPER.getValue();
            } else if(isAdjacentSubLine(leftRectangleFace, rightRectangleFace)){
                return ValidationResult.ADJACENT_SUB.getValue();
            } else if(isAdjacentSubLine(rightRectangleFace, leftRectangleFace)){
                return ValidationResult.ADJACENT_SUB.getValue();
            } else if(isLinePartiallyAdjacent(leftRectangleFace, rightRectangleFace)){
                return ValidationResult.ADJACENT_PARTIAL.getValue();
            }
        }
        return ValidationResult.NOT_ADJACENT.getValue();
    }

    public boolean isLinePartiallyAdjacent(Line left, Line right){

        return isPointYInsideOrTouching(left, right.getStartPoint()) ^ isPointYInsideOrTouching(left, right.getEndPoint());

    }

    public boolean isAdjacentSubLine(Line left, Line right){

        return isPointYInsideOrTouching(left, right.getStartPoint()) && isPointYInsideOrTouching(left, right.getEndPoint());

    }

}
