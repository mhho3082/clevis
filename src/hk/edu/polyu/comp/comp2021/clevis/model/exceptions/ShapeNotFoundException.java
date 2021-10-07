package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;

/**
 * Warns that the requested shape cannot be found.
 *
 * @author Ho Man Hin
 */
public class ShapeNotFoundException extends Exception {
    private final String name;

    /**
     * Creates a shape not found exception.
     *
     * @param name the shape name
     */
    public ShapeNotFoundException(String name) {
        super();
        this.name = name;
    }

    /**
     * Gets the shape name.
     *
     * @return the shape name.
     */
    public String getName() {
        return name;
    }
}
