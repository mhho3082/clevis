package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;

/**
 * An exception thrown when the name had been used already.
 *
 * @author Ho Man Hin
 */
public class DuplicateShapeNameException extends Exception {
    private final String name;

    /**
     * Creates a duplicate shape name exception.
     *
     * @param name the shape name
     */
    public DuplicateShapeNameException(String name) {
        super();
        this.name = name;
    }

    /**
     * Gets the shape name.
     *
     * @return the shape name
     */
    public String getName() {
        return name;
    }
}
