package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.EmptyGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandGroup extends Command {
    private static final String template = "group name n1 n2 ...";

    public CommandGroup(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    public ArrayList<String> exec()
            throws ShapeInsideGroupException, EmptyGroupException, ShapeNotFoundException, DuplicateShapeNameException {
        ArrayList<String> temp = new ArrayList<>(Arrays.asList(parsedInput).subList(2, parsedInput.length));

        model.group(parsedInput[1], temp);

        return null;
    }

    public void undo()
            throws ShapeInsideGroupException, ShapeNotFoundException, DuplicateShapeNameException {
        model.ungroup(parsedInput[1]);
    }

    public void check() throws WrongArgumentLengthException {
        if (this.parsedInput.length < 3) {
            throw new WrongArgumentLengthException(this.input, template);
        }
    }
}
