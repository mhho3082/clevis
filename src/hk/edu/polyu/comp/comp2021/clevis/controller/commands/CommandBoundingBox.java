package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.Config;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A command to call for "boundingbox".
 *
 * @author Ho Man Hin
 */
public class CommandBoundingBox extends Command {
    private static final String template = "boundingbox name";

    /**
     * Creates a command calling boundingBox().
     *
     * @param model   the model to call command to
     * @param command the command to call
     * @throws WrongArgumentLengthException warns of wrong argument length
     * @throws NotANumberException          warns of not-a-number arguments
     */
    public CommandBoundingBox(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    /**
     * Calls the command for execution.
     *
     * @return the bounding box
     * @throws ShapeInsideGroupException warns of one (or more) of shape inside group
     * @throws ShapeNotFoundException    warns of one (or more) of shape not found
     */
    @Override
    public ArrayList<String> exec() throws ShapeInsideGroupException, ShapeNotFoundException {
        BigDecimal[] tempOut = model.boundingBox(parsedInput[1]);
        ArrayList<String> temp = new ArrayList<>();

        temp.add(" xLeft: " + Config.roundForOutput(tempOut[0]));
        temp.add("  yTop: " + Config.roundForOutput(tempOut[1]));
        temp.add(" width: " + Config.roundForOutput(tempOut[2]));
        temp.add("height: " + Config.roundForOutput(tempOut[3]));

        return temp;
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
