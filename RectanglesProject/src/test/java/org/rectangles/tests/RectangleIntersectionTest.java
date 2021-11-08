package org.rectangles.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rectangles.entities.Rectangle;
import org.rectangles.operations.RectangleIntersection;
import org.rectangles.utils.ValidationResult;

public class RectangleIntersectionTest {
    RectangleIntersection rectangleIntersection;

    @Before
    public void init(){
        rectangleIntersection = new RectangleIntersection();
    }

    @Test
    public void validateInterceptionCorner(){
        Rectangle r1 = new Rectangle(30, 30, 40, 40);
        Rectangle r2 = new Rectangle(10, 10, 30, 30);

        String result = rectangleIntersection.validate(r1, r2);

        Assert.assertEquals(ValidationResult.INTERSECTION.getValue(), result);
    }

    @Test
    public void validateInterceptionSide(){
        Rectangle r1 = new Rectangle(30, 30, 40, 40);
        Rectangle r2 = new Rectangle(40, 20, 30, 30);

        String result = rectangleIntersection.validate(r1, r2);

        Assert.assertEquals(ValidationResult.INTERSECTION.getValue(), result);
    }

    @Test
    public void validateInterceptionCross(){
        Rectangle r2 = new Rectangle(20, 40, 60, 60);
        Rectangle r1 = new Rectangle(30, 30, 40, 40);

        String result = rectangleIntersection.validate(r1, r2);

        Assert.assertEquals(ValidationResult.INTERSECTION.getValue(), result);
    }

    @Test
    public void validateNoInterception(){
        Rectangle r1 = new Rectangle(10, 10, 20, 20);
        Rectangle r2 = new Rectangle(40, 50, 30, 30);

        String result = rectangleIntersection.validate(r1, r2);

        Assert.assertEquals(ValidationResult.NO_INTERSECTION.getValue(), result);
    }
}
