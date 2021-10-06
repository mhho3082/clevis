package hk.edu.polyu.comp.comp2021.clitoris.controller.commands;

import hk.edu.polyu.comp.comp2021.clitoris.model.Clitoris;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.ShapeNotFoundException;

import java.util.ArrayList;

public class CommandList extends Command {
    // list name

    public CommandList(Clitoris model, String command) {
        super(model, command);
    }

    public ArrayList<String> exec() throws ShapeInsideGroupException, ShapeNotFoundException {
        return model.list(parsedInput[1]);
    }

    public void undo() {
    }

    public boolean check() {
        if (this.parsedInput.length != 2) {
            return false;
        }

        return this.parsedInput[0].equals("list");
    }

    public boolean undoable() {
        return false;
    }
}
