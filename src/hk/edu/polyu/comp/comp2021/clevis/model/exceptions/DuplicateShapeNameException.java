package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;

import hk.edu.polyu.comp.comp2021.clevis.model.shapes.UserShape;

/**
 * An exception thrown when the name had been used already.
 *
 * @author Ho Man Hin
 */
public class DuplicateShapeNameException extends Exception {
    private final String name;

    public DuplicateShapeNameException(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
