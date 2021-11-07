package org.rectangles.operations;

import org.rectangles.entities.Line;
import org.rectangles.entities.Rectangle;
import org.rectangles.utils.ValidationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RectangleIntersection extends RectangleValidationAlgorithm{

    @Override
    public String validate(Rectangle a, Rectangle b) {

        List<Line> lines = breakRectangle(b);
        if(!getInterceptedLines(a, b, true).isEmpty()){
            return ValidationResult.INTERSECTION.getValue();
        }

        return ValidationResult.NO_INTERSECTION.getValue();
    }

    public Optional<List<Line>> getInterceptedLines(Rectangle r1, Rectangle r2, boolean breakOnFirst){
        List<Line> interceptedLines =  new ArrayList<>();

        for(Line line : breakRectangle(r2)){
            if(isLineInterception(r1, line)){
                interceptedLines.add(line);
                System.out.println("Intersection: " + line.toString());
                if(breakOnFirst){
                    return Optional.of(interceptedLines);
                }
            }
        }
        for(Line line : breakRectangle(r1)){
            if(isLineInterception(r2, line)){
                interceptedLines.add(line);
                System.out.println("Intersection: " + line.toString());
                if(breakOnFirst){
                    return Optional.of(interceptedLines);
                }
            }
        }
        if(!interceptedLines.isEmpty()){
            return Optional.of(interceptedLines);
        }
        return Optional.empty();
    }

    public boolean isLineInterception(Rectangle r, Line line){

        return isPointInside(r, line.getStartPoint()) ^ isPointInside(r, line.getEndPoint());

    }


}
