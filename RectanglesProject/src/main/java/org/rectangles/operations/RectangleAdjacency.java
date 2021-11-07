package org.rectangles.operations;

import org.rectangles.entities.Rectangle;
import org.rectangles.utils.ValidationResult;

public class RectangleAdjacency extends RectangleValidationAlgorithm{

        @Override
        public String validate(Rectangle a, Rectangle b) {

            if(a.getX() < b.getX() ){
                return areAdjacent(a, b);
            }

            return areAdjacent(b, a);

        }

        private String areAdjacent(Rectangle leftRectangle, Rectangle rightRectangle){
            //int topXMiddle = leftRectangle.getX() + leftRectangle.getWidth();
            if(leftRectangle.getX() + leftRectangle.getWidth() == rightRectangle.getX()){
                if(leftRectangle.getY() == rightRectangle.getY() &&
                        leftRectangle.getY() + leftRectangle.getHeight() == rightRectangle.getY() + rightRectangle.getHeight()){
                    return ValidationResult.ADJACENT_PROPER.getValue();
                } else if(leftRectangle.getY() > rightRectangle.getY() &&
                        leftRectangle.getY() + leftRectangle.getHeight() < rightRectangle.getY() + rightRectangle.getHeight()){
                    return ValidationResult.ADJACENT_SUB.getValue();
                } else if(leftRectangle.getY() < rightRectangle.getY() &&
                        leftRectangle.getY() + leftRectangle.getHeight() > rightRectangle.getY() + rightRectangle.getHeight()){
                    return ValidationResult.ADJACENT_SUB.getValue();
                } else if(leftRectangle.getY() > rightRectangle.getY() &&
                        leftRectangle.getY() + leftRectangle.getHeight() > rightRectangle.getY() + rightRectangle.getHeight()){
                    return ValidationResult.ADJACENT_PARTIAL.getValue();
                } else if(leftRectangle.getY() < rightRectangle.getY() &&
                        leftRectangle.getY() + leftRectangle.getHeight() < rightRectangle.getY() + rightRectangle.getHeight()){
                    return ValidationResult.ADJACENT_PARTIAL.getValue();
                }
            }
            return ValidationResult.NOT_ADJACENT.getValue();
        }

}
