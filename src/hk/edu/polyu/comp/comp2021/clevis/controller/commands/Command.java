package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.*;

import java.util.ArrayList;

public abstract class Command {
    protected final String input;
    protected final String[] parsedInput;
    protected final Clevis model;

    public Command(Clevis model, String command) {
        this.model = model;
        this.input = command;
        this.parsedInput = this.input.split(" ");
        check();
    }

    public abstract ArrayList<String> exec()
            throws ShapeInsideGroupException, ShapeNotFoundException, DuplicateShapeNameException, SizeIsZeroException, EmptyGroupException;

    public abstract void undo()
            throws ShapeInsideGroupException, EmptyGroupException, ShapeNotFoundException, DuplicateShapeNameException;

    public abstract boolean check();

    public boolean undoable() {
        return true;
    }

    public boolean isNotNumber(String in) {
        if (in == null) {
            return true;
        } else {
            try {
                double d = Double.parseDouble(in);
            } catch (NumberFormatException nfe) {
                return true;
            }
            return false;
        }
    }
}
