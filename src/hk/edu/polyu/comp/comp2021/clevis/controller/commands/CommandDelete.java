package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.util.ArrayList;

/**
 * A command to call for "delete".
 *
 * @author Ho Man Hin
 */
public class CommandDelete extends Command {
    private static final String template = "delete name";

    /**
     * Creates a command calling remove().
     *
     * @param model   the model to call command to
     * @param command the command to call
     * @throws WrongArgumentLengthException warns of wrong argument length
     * @throws NotANumberException          warns of not-a-number arguments
     */
    public CommandDelete(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    /**
     * Calls the command for execution.
     *
     * @return null
     * @throws ShapeInsideGroupException warns of shape inside group(s)
     * @throws ShapeNotFoundException    warns of shape not found
     */
    public ArrayList<String> exec() throws ShapeInsideGroupException, ShapeNotFoundException {
        model.remove(parsedInput[1]);

        return null;
    }

    /**
     * Undo the command.
     *
     * @throws ShapeNotFoundException      warns of shape not found
     * @throws DuplicateShapeNameException warns of duplicate shape name
     */
    public void undo() throws ShapeNotFoundException, DuplicateShapeNameException {
        model.restore(parsedInput[1]);
    }

    /**
     * Checks the command for validity.
     *
     * @throws WrongArgumentLengthException warns of wrong argument length (i.e. count)
     */
    public void check() throws WrongArgumentLengthException {
        if (this.parsedInput.length != 2) {
            throw new WrongArgumentLengthException(template);
        }
    }
}
