package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A command to call for "rectangle".
 *
 * @author Ho Man Hin
 */
public class CommandRectangle extends Command {
    private static final String template = "rectangle name xLeft yTop width height";

    /**
     * Creates a command calling addRectangle().
     *
     * @param model   the model to call command to
     * @param command the command to call
     * @throws WrongArgumentLengthException warns of wrong argument length
     * @throws NotANumberException          warns of not-a-number arguments
     */
    public CommandRectangle(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    /**
     * Calls the command for execution.
     *
     * @return null
     * @throws SizeIsZeroException         warns of zero-area shape
     * @throws DuplicateShapeNameException warns of duplicate shape name
     */
    @Override
    public ArrayList<String> exec()
            throws SizeIsZeroException, DuplicateShapeNameException {
        model.addRectangle(
                this.parsedInput[1],
                new BigDecimal(this.parsedInput[2]),
                new BigDecimal(this.parsedInput[3]),
                new BigDecimal(this.parsedInput[4]),
                new BigDecimal(this.parsedInput[5])
        );

        return null;
    }

    /**
     * Undo the command.
     *
     * @throws ShapeInsideGroupException warns of shape inside group
     * @throws ShapeNotFoundException    warns of shape not found
     */
    @Override
    public void undo()
            throws ShapeInsideGroupException, ShapeNotFoundException {
        model.remove(this.parsedInput[1]);
    }

    /**
     * Checks the command for validity.
     *
     * @throws NotANumberException          warns of not-a-number argument
     * @throws WrongArgumentLengthException warns of wrong argument length (i.e. count)
     */
    @Override
    public void check() throws NotANumberException, WrongArgumentLengthException {
        if (this.parsedInput.length != 6) {
            throw new WrongArgumentLengthException(template);
        }

        for (int i = 2; i < 6; i++) {
            if (isNotNumber(this.parsedInput[i])) {
                throw new NotANumberException(this.parsedInput[i], template);
            }
        }
    }
}
