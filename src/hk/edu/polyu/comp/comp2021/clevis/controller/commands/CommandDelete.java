package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.util.ArrayList;

public class CommandDelete extends Command {
    // delete name

    public CommandDelete(Clevis model, String command) {
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
}
