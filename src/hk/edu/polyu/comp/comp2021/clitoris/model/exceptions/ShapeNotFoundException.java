package hk.edu.polyu.comp.comp2021.clitoris.model.exceptions;

/**
 * Warns that the requested shape cannot be found.
 *
 * @author Ho Man Hin
 */
public class ShapeNotFoundException extends Exception {
    public ShapeNotFoundException() {
        super("Cannot find any related shape!");
    }
}
