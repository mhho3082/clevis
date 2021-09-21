package hk.edu.polyu.comp.comp2021.clitoris.model.exceptions;

/**
 * An exception thrown when the name had been used already.
 *
 * @author Ho Man Hin
 */
public class DuplicateShapeNameException extends Exception {
    private final String duplicateName;

    public DuplicateShapeNameException(String name) {
        super("Duplicate names for shape!");
        this.duplicateName = name;
    }

    public String getDuplicateName() {
        return this.duplicateName;
    }
}
