package org.rectangles.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rectangles.entities.Rectangle;
import org.rectangles.operations.RectangleContainment;
import org.rectangles.utils.ValidationResult;

public class RectangleContainmentTest {
    RectangleContainment rectangleContainment;

    @Before
    public void init(){
        rectangleContainment = new RectangleContainment();
    }

    @Test
    public void validateInterception(){
        Rectangle r1 = new Rectangle(30, 30, 40, 40);
        Rectangle r2 = new Rectangle(10, 10, 30, 30);

        String result = rectangleContainment.validate(r1, r2);

        Assert.assertNotEquals(ValidationResult.CONTAINMENT.getValue(), result);
    }

    @Test
    public void validateContainment(){
        Rectangle r1 = new Rectangle(30, 30, 20, 20);
        Rectangle r2 = new Rectangle(20, 20, 40, 40);

        String result = rectangleContainment.validate(r1, r2);

        Assert.assertEquals(ValidationResult.CONTAINMENT.getValue(), result);
    }

    @Test
    public void validateContainmentInverted(){
        Rectangle r1 = new Rectangle(20, 20, 50, 50);
        Rectangle r2 = new Rectangle(30, 30, 20, 20);

        String result = rectangleContainment.validate(r1, r2);

        Assert.assertEquals(ValidationResult.CONTAINMENT.getValue(), result);
    }

}
