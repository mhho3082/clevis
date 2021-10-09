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
 * A command to call for "line".
 *
 * @author Ho Man Hin
 */
public class CommandLine extends Command {
    private static final String template = "line name x1 x2 y1 y2";

    /**
     * Creates a command calling addLine().
     *
     * @param model   the model to call command to
     * @param command the command to call
     * @throws WrongArgumentLengthException warns of wrong argument length
     * @throws NotANumberException          warns of not-a-number arguments
     */
    public CommandLine(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    /**
     * Calls the command for execution.
     *
     * @return empty output
     * @throws SizeIsZeroException         warns of zero-area shape
     * @throws DuplicateShapeNameException warns of duplicate shape name
     */
    @Override
    public ArrayList<String> exec()
            throws SizeIsZeroException, DuplicateShapeNameException {
        model.addLine(
                this.parsedInput[1],
                new BigDecimal(this.parsedInput[2]),
                new BigDecimal(this.parsedInput[3]),
                new BigDecimal(this.parsedInput[4]),
                new BigDecimal(this.parsedInput[5])
        );

        return new ArrayList<>();
    }

    /**
     * Undo the command.
     *
     * @throws ShapeInsideGroupException warns of one (or more) of shape inside group
     * @throws ShapeNotFoundException    warns of one (or more) of shape not found
     */
    @Override
    public void undo()
            throws ShapeInsideGroupException, ShapeNotFoundException {
        model.remove(this.parsedInput[1]);
    }

    /**
     * Checks the command for validity.
     *
     * @throws WrongArgumentLengthException warns of wrong argument length (i.e. count)
     * @throws NotANumberException          warns of not-a-number arguments
     */
    @Override
    public void check() throws WrongArgumentLengthException, NotANumberException {
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
