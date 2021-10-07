package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;

import java.util.ArrayList;

public class CommandListAll extends Command {
    private static final String template = "listAll";

    public CommandListAll(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    public ArrayList<String> exec() {
        return model.listAll();
    }

    public void undo() {
    }

    public void check() throws WrongArgumentLengthException {
        if (this.parsedInput.length != 1) {
            throw new WrongArgumentLengthException(template);
        }
    }

    public boolean undoable() {
        return false;
    }
}
