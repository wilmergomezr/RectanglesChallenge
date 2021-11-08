package org.rectangles.operations;

import org.rectangles.entities.Line;
import org.rectangles.entities.Point;
import org.rectangles.entities.Rectangle;
import org.rectangles.utils.ValidationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RectangleIntersection extends RectangleValidationAlgorithm{

    @Override
    public String validate(Rectangle a, Rectangle b) {

        if(!getInterceptionPoints(a, b).isEmpty()){
            return ValidationResult.INTERSECTION.getValue();
        }

        return ValidationResult.NO_INTERSECTION.getValue();
    }

    public String isIntersection(Optional<List<Point>> intersectionPoints) {

        if(!intersectionPoints.isEmpty()){
            return ValidationResult.INTERSECTION.getValue();
        }

        return ValidationResult.NO_INTERSECTION.getValue();
    }

    public Optional<List<Point>> getInterceptionPoints(Rectangle rectangle1, Rectangle rectangle2){
        List<Point> interceptionPoints = new ArrayList<>();

        for (Line lineV : breakRectangleVertical(rectangle1)){
            for(Line lineH : breakRectangleHorizontal(rectangle2)){
                if(isLineInterception(lineH, lineV)){
                    interceptionPoints.add(new Point(lineV.getStartPoint().getX(), lineH.getStartPoint().getY()));
                }
            }
        }

        for (Line lineV : breakRectangleVertical(rectangle2)){
            for(Line lineH : breakRectangleHorizontal(rectangle1)){
                if(isLineInterception(lineH, lineV)){
                    interceptionPoints.add(new Point(lineV.getStartPoint().getX(), lineH.getStartPoint().getY()));
                }
            }
        }

        if(!interceptionPoints.isEmpty()){
            System.out.println("Total points: " + interceptionPoints.size());
            return Optional.of(interceptionPoints);
        }

        return Optional.empty();
    }

    public boolean isLineInterception(Line H, Line V){
        return H.getStartPoint().getY() > V.getStartPoint().getY() && H.getStartPoint().getY() < V.getEndPoint().getY() &&
                V.getStartPoint().getX() > H.getStartPoint().getX() && V.getStartPoint().getX() < H.getEndPoint().getX();
    }

}
