package hk.edu.polyu.comp.comp2021.clitoris.controller.commands;

import hk.edu.polyu.comp.comp2021.clitoris.model.Clitoris;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.ShapeNotFoundException;

import java.util.ArrayList;

public class CommandDelete extends Command {
    // delete name

    public CommandDelete(Clitoris model, String command) {
        super(model, command);
    }

    public ArrayList<String> exec() throws ShapeInsideGroupException, ShapeNotFoundException {
        model.remove(parsedInput[1]);

        return null;
    }

    public void undo() throws ShapeNotFoundException, DuplicateShapeNameException {
        model.restore(parsedInput[1]);
    }

    public boolean check() {
        if (this.parsedInput.length != 2) {
            return false;
        }

        return this.parsedInput[0].equals("delete");
    }

    public boolean undoable() {
        return true;
    }
}
