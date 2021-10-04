package hk.edu.polyu.comp.comp2021.clitoris.model.exceptions;

public class SizeIsZeroException extends Exception {

    public SizeIsZeroException() {
        super("The size of the shape cannot be zero!");
    }
}
