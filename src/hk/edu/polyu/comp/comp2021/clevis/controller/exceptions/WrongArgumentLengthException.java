package hk.edu.polyu.comp.comp2021.clevis.controller.exceptions;

/**
 * Warns that the user input has wrong argument length (i.e. count).
 *
 * @author Ho Man Hin
 */
public class WrongArgumentLengthException extends Exception {
    private final String template;

    /**
     * Creates a wrong argument length exception.
     *
     * @param template the template of the command
     */
    public WrongArgumentLengthException(String template) {
        super();
        this.template = template;
    }

    /**
     * Gets the template.
     *
     * @return the template
     */
    public String getTemplate() {
        return template;
    }
}
