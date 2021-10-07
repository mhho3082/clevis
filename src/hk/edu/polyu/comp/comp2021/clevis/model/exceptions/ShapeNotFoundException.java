package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;

/**
 * Warns that the requested shape cannot be found.
 *
 * @author Ho Man Hin
 */
public class ShapeNotFoundException extends Exception {
    private final String name;

    public ShapeNotFoundException(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
