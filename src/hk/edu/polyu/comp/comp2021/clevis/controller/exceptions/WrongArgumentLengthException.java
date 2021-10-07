package hk.edu.polyu.comp.comp2021.clevis.controller.exceptions;

public class WrongArgumentLengthException extends Exception {
    private final String template;

    public WrongArgumentLengthException(String template) {
        super();
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }
}
