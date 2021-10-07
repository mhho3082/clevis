package hk.edu.polyu.comp.comp2021.clevis.controller.exceptions;

/**
 * Warns that an argument input is not a number,
 * while it should be.
 *
 * @author Ho Man Hin
 */
public class NotANumberException extends Exception {
    private final String wrongInput;
    private final String template;

    /**
     * Creates a not a number exception.
     *
     * @param wrongInput the input that should be a number, but isn't
     * @param template   the template of the command
     */
    public NotANumberException(String wrongInput, String template) {
        super();
        this.wrongInput = wrongInput;
        this.template = template;
    }

    /**
     * Gets the wrong input
     *
     * @return the wrong input
     */
    public String getWrongInput() {
        return wrongInput;
    }

    /**
     * Gets the template
     *
     * @return the template
     */
    public String getTemplate() {
        return template;
    }
}
