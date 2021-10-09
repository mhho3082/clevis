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

/**
 * A command to call for "group".
 *
 * @author Ho Man Hin
 */
public class CommandGroup extends Command {
    private static final String template = "group name shapeName...";

    /**
     * Creates a command calling group().
     *
     * @param model   the model to call command to
     * @param command the command to call
     * @throws WrongArgumentLengthException warns of wrong argument length
     * @throws NotANumberException          warns of not-a-number arguments
     */
    public CommandGroup(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    /**
     * Calls the command for execution.
     *
     * @return null
     * @throws ShapeInsideGroupException   warns of one (or more) of shape inside group
     * @throws EmptyGroupException         warns of empty group
     * @throws ShapeNotFoundException      warns of one (or more) of shape not found
     * @throws DuplicateShapeNameException warns of duplicate shape name
     */
    @Override
    public ArrayList<String> exec()
            throws ShapeInsideGroupException, EmptyGroupException, ShapeNotFoundException, DuplicateShapeNameException {
        ArrayList<String> temp = new ArrayList<>(Arrays.asList(parsedInput).subList(2, parsedInput.length));

        model.group(parsedInput[1], temp);

        return null;
    }

    /**
     * Undo the command.
     *
     * @throws ShapeInsideGroupException   warns of shape inside group
     * @throws ShapeNotFoundException      warns of one (or more) of shape not found
     * @throws DuplicateShapeNameException warns of duplicate shape name
     */
    @Override
    public void undo()
            throws ShapeInsideGroupException, ShapeNotFoundException, DuplicateShapeNameException {
        model.ungroup(parsedInput[1]);
    }

    /**
     * Checks the command for validity.
     *
     * @throws WrongArgumentLengthException warns of wrong argument length (i.e. count)
     */
    @Override
    public void check() throws WrongArgumentLengthException {
        if (this.parsedInput.length < 3) {
            throw new WrongArgumentLengthException(template);
        }
    }
}
