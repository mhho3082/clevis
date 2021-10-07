package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.util.ArrayList;

public class CommandDelete extends Command {
    private static final String template = "delete name";

    public CommandDelete(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    public ArrayList<String> exec() throws ShapeInsideGroupException, ShapeNotFoundException {
        model.remove(parsedInput[1]);

        return null;
    }

    public void undo() throws ShapeNotFoundException, DuplicateShapeNameException {
        model.restore(parsedInput[1]);
    }

    public void check() throws WrongArgumentLengthException {
        if (this.parsedInput.length != 2) {
            throw new WrongArgumentLengthException(template);
        }
    }
}
