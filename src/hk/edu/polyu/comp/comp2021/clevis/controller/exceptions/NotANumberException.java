package hk.edu.polyu.comp.comp2021.clevis.controller.exceptions;

public class NotANumberException extends Exception {
    private final String wrongInput;
    private final String template;

    public NotANumberException(String wrongInput, String template) {
        super();
        this.wrongInput = wrongInput;
        this.template = template;
    }

    public String getWrongInput() {
        return wrongInput;
    }

    public String getTemplate() {
        return template;
    }
}
