package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.util.ArrayList;

public class CommandIntersect extends Command {
    private static final String template = "intersect name1 name2";

    public CommandIntersect(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    public ArrayList<String> exec()
            throws ShapeInsideGroupException, ShapeNotFoundException {
        String result = String.valueOf(model.intersect(parsedInput[1], parsedInput[2]));

        ArrayList<String> temp = new ArrayList<>();
        temp.add(result);
        return temp;
    }

    public void undo() {
    }

    public void check() throws WrongArgumentLengthException {
        if (this.parsedInput.length != 3) {
            throw new WrongArgumentLengthException(template);
        }
    }

    public boolean undoable() {
        return false;
    }
}
