package org.rectangles.utils;

public enum IntersectionResult {
    INTERSECTION("Intersection"), NO_INTERSECTION("No Intersection");
    private String value;

    private IntersectionResult(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
