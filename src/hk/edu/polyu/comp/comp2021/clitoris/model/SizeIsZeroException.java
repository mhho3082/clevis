package hk.edu.polyu.comp.comp2021.clitoris.model;

public class SizeIsZeroException extends Exception {
    private final String shapeName;

    public SizeIsZeroException(String name) {
        super("The size of the shape cannot be zero!");
        this.shapeName = name;
    }

    public String getShapeName() {
        return this.shapeName;
    }
}
