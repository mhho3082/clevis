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

/**
 * A command to call for "ungroup".
 *
 * @author Ho Man Hin
 */
public class CommandUngroup extends Command {
    private static final String template = "ungroup name";

    private final ArrayList<String> GroupedNames;

    /**
     * Creates a command calling ungroup().
     *
     * @param model   the model to call command to
     * @param command the command to call
     * @throws WrongArgumentLengthException warns of wrong argument length
     * @throws NotANumberException          warns of not-a-number arguments
     * @throws ShapeNotFoundException       warns of shape inside group
     */
    public CommandUngroup(Clevis model, String command) throws ShapeNotFoundException, WrongArgumentLengthException, NotANumberException {
        super(model, command);

        GroupedNames = ((Group) model.find(parsedInput[1])).getUserShapesNames();
    }

    /**
     * Executes the command
     *
     * @return empty output
     * @throws ShapeInsideGroupException   warns of shape inside group
     * @throws ShapeNotFoundException      warns of shape not found
     * @throws DuplicateShapeNameException warns of duplicate shape name
     */
    @Override
    public ArrayList<String> exec()
            throws ShapeInsideGroupException, ShapeNotFoundException, DuplicateShapeNameException {
        model.ungroup(parsedInput[1]);

        return new ArrayList<>();
    }

    /**
     * Undo the command.
     *
     * @throws ShapeInsideGroupException   warns of shape inside group
     * @throws EmptyGroupException         warns of empty group
     * @throws ShapeNotFoundException      warns of shape not found
     * @throws DuplicateShapeNameException warns of duplicate shape name
     */
    @Override
    public void undo()
            throws ShapeInsideGroupException, EmptyGroupException, ShapeNotFoundException, DuplicateShapeNameException {
        model.group(parsedInput[1], this.GroupedNames);
    }

    /**
     * Checks the command for validity
     *
     * @throws WrongArgumentLengthException warns of wrong argument length (i.e. count)
     */
    @Override
    public void check() throws WrongArgumentLengthException {
        if (this.parsedInput.length != 2) {
            throw new WrongArgumentLengthException(template);
        }
    }
}
