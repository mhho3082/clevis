package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;

import java.util.ArrayList;

public class CommandListAll extends Command {
    // listAll

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
            throw new WrongArgumentLengthException();
        }
    }

    public boolean undoable() {
        return false;
    }
}
