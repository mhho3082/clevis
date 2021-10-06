package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.*;

import java.util.ArrayList;

public abstract class Command {
    protected final String input;
    protected final String[] parsedInput;
    protected final Clevis model;

    public Command(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        this.model = model;
        this.input = command;
        this.parsedInput = this.input.split(" ");
        check();
    }

    public abstract ArrayList<String> exec()
            throws ShapeInsideGroupException, ShapeNotFoundException, DuplicateShapeNameException, SizeIsZeroException, EmptyGroupException;

    public abstract void undo()
            throws ShapeInsideGroupException, EmptyGroupException, ShapeNotFoundException, DuplicateShapeNameException;

    public abstract void check() throws WrongArgumentLengthException, NotANumberException;

    public boolean undoable() {
        return true;
    }

    public boolean isNotNumber(String in) {
        if (in == null) {
            return true;
        } else {
            try {
                Double.parseDouble(in);
            } catch (NumberFormatException nfe) {
                return true;
            }
            return false;
        }
    }
}
