package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;

import java.util.ArrayList;

/**
 * A command to call for "listAll".
 *
 * @author Ho Man Hin
 */
public class CommandListAll extends Command {
    private static final String template = "listAll";

    /**
     * Creates a command calling listAll().
     *
     * @param model   the model to call command to
     * @param command the command to call
     * @throws WrongArgumentLengthException warns of wrong argument length
     * @throws NotANumberException          warns of not-a-number arguments
     */
    public CommandListAll(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    /**
     * Calls the command for execution.
     *
     * @return the general list of details
     */
    public ArrayList<String> exec() {
        return model.listAll();
    }

    /**
     * Undo the command (not undoable).
     */
    public void undo() {
    }

    /**
     * Checks the command for validity.
     *
     * @throws WrongArgumentLengthException warns of wrong argument length (i.e. count)
     */
    public void check() throws WrongArgumentLengthException {
        if (this.parsedInput.length != 1) {
            throw new WrongArgumentLengthException(template);
        }
    }

    /**
     * Checks if the command can be undo-ed.
     *
     * @return that the command is not undoable
     */
    public boolean undoable() {
        return false;
    }
}
