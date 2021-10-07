package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.util.ArrayList;

public class CommandList extends Command {
    private static final String template = "list name";

    public CommandList(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    public ArrayList<String> exec() throws ShapeInsideGroupException, ShapeNotFoundException {
        return model.list(parsedInput[1]);
    }

    public void undo() {
    }

    public void check() throws WrongArgumentLengthException {
        if (this.parsedInput.length != 2) {
            throw new WrongArgumentLengthException(template);
        }
    }

    public boolean undoable() {
        return false;
    }
}
