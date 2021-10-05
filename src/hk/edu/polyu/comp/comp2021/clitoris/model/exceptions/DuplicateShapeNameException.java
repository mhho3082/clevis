package hk.edu.polyu.comp.comp2021.clitoris.model.exceptions;

/**
 * An exception thrown when the name had been used already.
 *
 * @author Ho Man Hin
 */
public class DuplicateShapeNameException extends Exception {
    public DuplicateShapeNameException() {
        super("Duplicate names for shape!");
    }
}
