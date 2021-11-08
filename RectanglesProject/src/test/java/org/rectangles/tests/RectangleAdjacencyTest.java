package org.rectangles.tests;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rectangles.entities.Rectangle;
import org.rectangles.operations.RectangleAdjacency;
import org.rectangles.utils.ValidationResult;

public class RectangleAdjacencyTest {

    RectangleAdjacency rectangleAdjacency;

    @Before
    public void init(){
        rectangleAdjacency = new RectangleAdjacency();
    }

    @Test
    public void validateAdjacentProper(){
        Rectangle r1 = new Rectangle(10, 10, 20, 20);
        Rectangle r2 = new Rectangle(30, 10, 20, 20);

        String result = rectangleAdjacency.validate(r1, r2);

        Assert.assertEquals(ValidationResult.ADJACENT_PROPER.getValue(), result);
    }

    @Test
    public void validateAdjacentProperInverted(){
        Rectangle r2 = new Rectangle(10, 10, 20, 20);
        Rectangle r1 = new Rectangle(30, 10, 20, 20);

        String result = rectangleAdjacency.validate(r1, r2);

        Assert.assertEquals(ValidationResult.ADJACENT_PROPER.getValue(), result);
    }

    @Test
    public void validateAdjacentSubLine(){
        Rectangle r1 = new Rectangle(10, 10, 20, 30);
        Rectangle r2 = new Rectangle(30, 10, 10, 15);

        String result = rectangleAdjacency.validate(r1, r2);

        Assert.assertEquals(ValidationResult.ADJACENT_SUB.getValue(), result);
    }

    @Test
    public void validateAdjacentSubLineInverted(){
        Rectangle r1 = new Rectangle(10, 10, 20, 10);
        Rectangle r2 = new Rectangle(30, 5, 10, 30);

        String result = rectangleAdjacency.validate(r1, r2);

        Assert.assertEquals(ValidationResult.ADJACENT_SUB.getValue(), result);
    }

    @Test
    public void validateAdjacentPartialInverted(){
        Rectangle r1 = new Rectangle(10, 10, 20, 20);
        Rectangle r2 = new Rectangle(30, 5, 10, 15);

        String result = rectangleAdjacency.validate(r1, r2);

        Assert.assertEquals(ValidationResult.ADJACENT_PARTIAL.getValue(), result);
    }

    @Test
    public void validateAdjacentPartial(){
        Rectangle r1 = new Rectangle(10, 10, 20, 30);
        Rectangle r2 = new Rectangle(30, 15, 10, 35);

        String result = rectangleAdjacency.validate(r1, r2);

        Assert.assertEquals(ValidationResult.ADJACENT_PARTIAL.getValue(), result);
    }

    @Test
    public void validateNotAdjacent(){
        Rectangle r1 = new Rectangle(10, 10, 20, 20);
        Rectangle r2 = new Rectangle(35, 10, 10, 15);

        String result = rectangleAdjacency.validate(r1, r2);

        Assert.assertEquals(ValidationResult.NOT_ADJACENT.getValue(), result);
    }

    @Test
    public void validateNotAdjacent2(){
        Rectangle r1 = new Rectangle(10, 10, 20, 20);
        Rectangle r2 = new Rectangle(30, 40, 10, 15);

        String result = rectangleAdjacency.validate(r1, r2);

        Assert.assertEquals(ValidationResult.NOT_ADJACENT.getValue(), result);
    }
}
