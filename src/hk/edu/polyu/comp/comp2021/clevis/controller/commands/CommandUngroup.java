package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.EmptyGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;
import hk.edu.polyu.comp.comp2021.clevis.model.shapes.Group;

import java.util.ArrayList;

public class CommandUngroup extends Command {
    // ungroup name

    private final ArrayList<String> GroupedNames;

    public CommandUngroup(Clevis model, String command) throws ShapeNotFoundException, WrongArgumentLengthException, NotANumberException {
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

    public void check() throws WrongArgumentLengthException {
        if (this.parsedInput.length != 2) {
            throw new WrongArgumentLengthException();
        }
    }
}
