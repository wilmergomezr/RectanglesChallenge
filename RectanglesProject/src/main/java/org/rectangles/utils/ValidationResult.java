package org.rectangles.utils;

public enum ValidationResult {
    INTERSECTION("Intersection"), NO_INTERSECTION("No Intersection"), CONTAINMENT("Containment"),
    NO_CONTAINMENT("No Containment"), ADJACENT_SUB("Adjacent (sub-line)"), ADJACENT_PROPER("Adjacent (Proper)"),
    ADJACENT_PARTIAL("Adjacent (Partial)"), NOT_ADJACENT("Not Adjacent");
    private String value;

    private ValidationResult(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
