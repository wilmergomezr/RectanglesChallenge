# Rectangles Validations

Author: Wilmer Gomez Rueda

E-Mail: wilmer.gomezr@gmail.com

## Problem Description

![Problem](RectanglesProgrammingSample.pdf)

Is required to create 3 algorithm that let us evaluate rectangles. The three validations required are intersection, containment and adjacency.

### Intersection 
The target here is to determine if two rectangles has intersecting lines between them. For this algorithm I have the assumption that two lines intercepted can be just touching themselves, they have to be effectively crossing.

And it have to return the two possible results (Intersection or No Intersection) and the intercepting points. 

### Containment
The target of this algorithm is to determine if one rectangle is contained into another one, so all the vertices of the small rectangle are inside the big one. For this containment, the rectangles can´t share any side.
This algorithm should return the two possible scenarios (Containment or No Containment)

### Adjacency
In this algorithm I have to identify if two rectangles are side by side. To identify if the two rectangles are adjacent they have to share a side. A technical way of seeing this is that the x position of the right face of one rectangle, have to be equal to the x position of the left face of the right rectangle.
   
This validations should return: 
* Adjacent Proper, if the touching faces are the same.
* Adjacent Sub-line, if one face in smaller than the other and is fully touching the bigger one.
* Adjacent Partial, if the faces are in contact only on a portion.
* Not Adjacent, if they don´t share a face or if they are not side by side.

## Approach
### Entities
For the implementation I decided to create 3 different entities, that all have a purpose on the code that we are going to review.
All the values used to define and evaluate rectangles are integers,

#### Point
Is used for validations and to return the intercepting points on the intersection algorithm.

For all the implementation we assume that all the rectangles are in the positive side of the imaginary plane. 

    public class Point {
         private int x;
         private int y;

#### Line
A basic line implementation that consists in two points, a start and an end point. is used for almost all the algorithm implemented in this solution.

    public class Line {
        private Point startPoint;
        private Point endPoint; 

#### Rectangle
The main entity, is based on a start point, and its respective Width and height.
One consideration that we do in the pre-processing is that the start point of the rectangle has to be the top left corner. For this I implemented a validation that fix the rectangle if a negative values is sent.   

    private Rectangle validateAndFix(Rectangle r){
        if(r.getHeight() < 0){
            r.setY( r.getY() + r.getHeight());
            r.setHeight( r.getHeight() * -1 );
        }
        if(r.getWidth() < 0){
            r.setX( r.getX() + r.getWidth());
            r.setWidth( r.getWidth() * -1 );
        }
        return r;
    }
    
And the rectangle entity looks like this

    public class Rectangle {
        private int x;
        private int y;
        private int width;
        private int height;

### Intersection
On this algorithm the approach taken was to split the rectangle on its border lines. 
And compare the vertical lines of one rectangle, against the horizontals of the another rectangle. And returning all the interception points.

Part of this validation is

    for (Line lineV : breakRectangleVertical(rectangle1)){
        for(Line lineH : breakRectangleHorizontal(rectangle2)){
            if(isLineInterception(lineH, lineV)){
                interceptionPoints.add(new Point(lineV.getStartPoint().getX(), lineH.getStartPoint().getY()));
            }
        }
    }

### Containment
For this implementation I calculate a diagonal of the rectangles and if the extreme points of this line is inside the other rectangle, then the rectangle is contained.

part of the implementation is

    Line rectangleDiagonal = new Line(rectangle2.getX(), rectangle2.getY(), 
                rectangle2.getX() + rectangle2.getWidth(), rectangle2.getY() + rectangle2.getHeight());
    if(isLineInside(rectangle1, rectangleDiagonal)){
        System.out.println("Is inside: " + rectangleDiagonal.toString());
        return true;
    }

### Adjacency

for this validation first I determine the locations of the rectangles, to identify the faces that might be in contact. 
This way I can say that the right edge of the left rectangle must be in contact with the left edge of the other rectangle. 

After this validation we have to validate which kind of adjacency they have (proper, sub-line or partial)

And if they are not in contact I returned "Not Adjacent" 

This is the method with the validations for each type of adjacency

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

###Tools
 * Gradle
 * JUnit
 * Jacoco

## How to Use

I implemented two ways of using this application, the command line and a GUI for an easiest way of testing and validate.

###Build

First into the RectanglesChallenge\RectanglesProject directory an run the next command

     gradle clean build
     
After successfully build the project, run the Application task with gradle

     gradle run

### GUI

On this GUI you are able to draw rectangles, with your first click you define the start point of the rectangle, on the screen you will see a red dot representing this.

![First Click](files/firstClick.png)

The next click will define the width and height of the rectangle, and it'll be drawn on the screen.

![Second Click](files/secondClick.png)

There will only be two rectangles on the screen at the same time, so the last to rectangles drawn will remain, and any older than those will be removed.
when a second rectangle is drawn, all the validations are executed and the results gets showed on the screen

![Two Rectangles](files/twoRectangles.png)
![Validations](files/twoRectangles2.png)

For the intersections there is and extra visual indication, were we will be able to see the intersection points as blue dots.

![Validations](files/Intersection.png) 

## Testing
### Unit Tests
I created unit tests for all the classes, to cover not only the branches of the code. 
It also cover the possible technical scenarios than might lead to incorrect results.

There are a couple of exclusions of this tests, because on my point of view those packages where no relevant for the exercise.
the excluded packages were:
* Entities: as entities they don't contain any logic, so there is no needed a test.
* UI: on this case the UI is created for easy testing so didn't seem important to me to test this package either.
 
### Coverage

I added jacoco to validate the test coverage, in this case I get 100% of test coverage and 96% on the branches, as shown in the screenshot below.

![Coverage](files/jacoco.png)