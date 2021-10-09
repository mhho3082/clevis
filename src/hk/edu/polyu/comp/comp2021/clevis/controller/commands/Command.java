package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.*;

import java.util.ArrayList;

/**
 * The general abstract class for command objects.
 *
 * @author Ho Man Hin
 */
public abstract class Command {
    /**
     * User input
     */
    protected final String input;

    /**
     * The user input, split by " "
     */
    protected final String[] parsedInput;

    /**
     * The model used
     */
    protected final Clevis model;

    /**
     * Creates a command object.
     *
     * @param model   the model to call command to
     * @param command the command to call
     * @throws WrongArgumentLengthException warns of wrong argument length
     * @throws NotANumberException          warns of not-a-number arguments
     */
    public Command(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        this.model = model;
        this.input = command;
        this.parsedInput = this.input.split(" ");
        check();
    }

    /**
     * Calls the command for execution.
     *
     * @return a list of output
     * @throws ShapeInsideGroupException   warns of one (or more) of shape inside group(s)
     * @throws ShapeNotFoundException      warns of one (or more) of shape not found
     * @throws DuplicateShapeNameException warns of duplicate shape name
     * @throws SizeIsZeroException         warns of zero area shapes
     * @throws EmptyGroupException         warns of empty group
     */
    public abstract ArrayList<String> exec()
            throws ShapeInsideGroupException, ShapeNotFoundException, DuplicateShapeNameException, SizeIsZeroException, EmptyGroupException;

    /**
     * Undo the command (if undoable).
     *
     * @throws ShapeInsideGroupException   warns of one (or more) of shape inside group(s)
     * @throws EmptyGroupException         warns of empty group
     * @throws ShapeNotFoundException      warns one (or more) of shape not found
     * @throws DuplicateShapeNameException warns of duplicate shape name
     */
    public abstract void undo()
            throws ShapeInsideGroupException, EmptyGroupException, ShapeNotFoundException, DuplicateShapeNameException;

    /**
     * Checks the command for validity.
     *
     * @throws WrongArgumentLengthException warns of wrong argument length (i.e. count)
     * @throws NotANumberException          warns of not-a-number arguments
     */
    public abstract void check() throws WrongArgumentLengthException, NotANumberException;

    /**
     * Checks if the command can be undo-ed.
     * Defaults to true.
     * Only intersect, boundingBox, list, listAll (if basic commands) return false.
     *
     * @return if the command is undoable
     */
    public boolean undoable() {
        return true;
    }

    /**
     * Checks if a particular argument is not a number.
     *
     * @param in a particular argument
     * @return whether the argument is not a number
     */
    public boolean isNotNumber(String in) {
        if (in == null) {
            return true;
        } else {
            try {
                Double.parseDouble(in);
            } catch (NumberFormatException e) {
                return true;
            }
            return false;
        }
    }
}
