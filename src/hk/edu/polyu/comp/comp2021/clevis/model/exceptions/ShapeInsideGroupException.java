package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;

/**
 * Warns that the shape accessed exists,
 * but is not on the uppermost layer (shallow),
 * so the access is illegal.
 *
 * This can be caused when a shape inside a group was accessed.
 *
 * @author Ho Man Hin
 */
public class ShapeInsideGroupException extends Exception {
    private final String shapeName;

    public ShapeInsideGroupException(String shapeName) {
        super();
        this.shapeName = shapeName;
    }

    public String getShapeName() {
        return shapeName;
    }
}
