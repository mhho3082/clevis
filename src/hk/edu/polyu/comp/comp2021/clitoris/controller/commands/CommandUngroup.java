package hk.edu.polyu.comp.comp2021.clitoris.controller.commands;

import hk.edu.polyu.comp.comp2021.clitoris.model.Clitoris;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.EmptyGroupException;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.ShapeNotFoundException;
import hk.edu.polyu.comp.comp2021.clitoris.model.shapes.Group;

import java.util.ArrayList;

public class CommandUngroup extends Command {
    // ungroup name

    private final ArrayList<String> GroupedNames;

    public CommandUngroup(Clitoris model, String command) throws ShapeNotFoundException {
        super(model, command);

        GroupedNames = ((Group) model.find(parsedInput[0])).getUserShapesNames();
    }

    public ArrayList<String> exec()
            throws ShapeInsideGroupException, ShapeNotFoundException, DuplicateShapeNameException {
        model.ungroup(parsedInput[1]);

        return null;
    }

    public void undo()
            throws ShapeInsideGroupException, EmptyGroupException, ShapeNotFoundException, DuplicateShapeNameException {
        model.group(parsedInput[1], this.GroupedNames);
    }

    public boolean check() {
        if (this.parsedInput.length != 2) {
            return false;
        }

        return this.parsedInput[0].equals("ungroup");
    }

    public boolean undoable() {
        return true;
    }
}
