package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.util.ArrayList;

/**
 * A command to call for "intersect".
 *
 * @author Ho Man hin
 */
public class CommandIntersect extends Command {
    private static final String template = "intersect name1 name2";

    /**
     * Creates a command calling intersect().
     *
     * @param model   the model to call command to
     * @param command the command to call
     * @throws WrongArgumentLengthException warns of wrong argument length
     * @throws NotANumberException          warns of not-a-number arguments
     */
    public CommandIntersect(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    /**
     * Calls the command for execution.
     *
     * @return if the two shapes intersect
     * @throws ShapeInsideGroupException warns of one (or more) of shape inside group
     * @throws ShapeNotFoundException    warns of one (or more) of shape not found
     */
    public ArrayList<String> exec()
            throws ShapeInsideGroupException, ShapeNotFoundException {
        String result = String.valueOf(model.intersect(parsedInput[1], parsedInput[2]));

        ArrayList<String> temp = new ArrayList<>();
        temp.add(result);
        return temp;
    }

    /**
     * Undo the command (not undoable).
     */
    public void undo() {
    }

    /**
     * Checks the command for validity.
     *
     * @throws WrongArgumentLengthException warns of wrong argument length(i.e. count)
     */
    public void check() throws WrongArgumentLengthException {
        if (this.parsedInput.length != 3) {
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
