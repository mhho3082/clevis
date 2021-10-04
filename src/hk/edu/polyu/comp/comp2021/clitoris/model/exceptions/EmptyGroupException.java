package hk.edu.polyu.comp.comp2021.clitoris.model.exceptions;

/**
 * Warns that the group is empty;
 * i.e. devoid of grouped shapes.
 *
 * @author Ho Man Hin
 */
public class EmptyGroupException extends Exception {
    public EmptyGroupException() {
        super("Group cannot be empty!");
    }
}
