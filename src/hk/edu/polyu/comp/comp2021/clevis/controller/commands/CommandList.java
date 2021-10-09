package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.util.ArrayList;

/**
 * A command to call for "list".
 *
 * @author Ho Man Hin
 */
public class CommandList extends Command {
    private static final String template = "list name";

    /**
     * Create a command calling list().
     *
     * @param model   the model to call command to
     * @param command the command to call
     * @throws WrongArgumentLengthException warns of wrong argument length
     * @throws NotANumberException          warns of not-a-number arguments
     */
    public CommandList(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    /**
     * Calls the command for execution.
     *
     * @return the list of details
     * @throws ShapeInsideGroupException warns of shape inside group
     * @throws ShapeNotFoundException    warns of shape not found
     */
    @Override
    public ArrayList<String> exec() throws ShapeInsideGroupException, ShapeNotFoundException {
        return model.list(parsedInput[1]);
    }

    /**
     * Undo the command (not undoable).
     */
    @Override
    public void undo() {
    }

    /**
     * Checks the command for validity.
     *
     * @throws WrongArgumentLengthException warns of wrong argument length (i.e. count)
     */
    @Override
    public void check() throws WrongArgumentLengthException {
        if (this.parsedInput.length != 2) {
            throw new WrongArgumentLengthException(template);
        }
    }

    /**
     * Checks if the command can be undo-ed.
     *
     * @return that the command is not undoable
     */
    @Override
    public boolean undoable() {
        return false;
    }
}
