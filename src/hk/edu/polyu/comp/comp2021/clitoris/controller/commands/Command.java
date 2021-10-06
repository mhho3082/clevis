package hk.edu.polyu.comp.comp2021.clitoris.controller.commands;

import hk.edu.polyu.comp.comp2021.clitoris.model.Clitoris;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.*;

import java.util.ArrayList;

public abstract class Command {
    protected final String input;
    protected final String[] parsedInput;
    protected final Clitoris model;

    public Command(Clitoris model, String command) {
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

    public abstract boolean undoable();

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
