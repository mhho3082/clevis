package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.NoShapeContainsPointException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A command to call for "pick-and-move".
 *
 * @author Ho Man Hin
 */
public class CommandPickAndMove extends Command {
    private static final String template = "pick-and-move x y dx dy";

    /**
     * Creates a command calling pickAndMove().
     *
     * @param model   the model to call command to
     * @param command the command to call
     * @throws WrongArgumentLengthException warns of wrong argument length
     * @throws NotANumberException          warns of not-a-number arguments
     */
    public CommandPickAndMove(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    /**
     * Executes the command.
     *
     * @return null
     * @throws ShapeNotFoundException        warns of one (or more) of shape not found
     * @throws NoShapeContainsPointException warns of no shape containing point
     */
    public ArrayList<String> exec() throws ShapeNotFoundException, NoShapeContainsPointException {
        model.pickAndMove(
                new BigDecimal(parsedInput[1]),
                new BigDecimal(parsedInput[2]),
                new BigDecimal(parsedInput[3]),
                new BigDecimal(parsedInput[4])
        );

        return null;
    }

    /**
     * Undo the command.
     *
     * @throws ShapeNotFoundException        warns of one (or more) of shape not found
     * @throws NoShapeContainsPointException warns of no shape containing point
     */
    public void undo() throws ShapeNotFoundException, NoShapeContainsPointException {
        model.pickAndMove(
                new BigDecimal(parsedInput[1]).add(new BigDecimal(parsedInput[3])),
                new BigDecimal(parsedInput[2]).add(new BigDecimal(parsedInput[4])),
                new BigDecimal(parsedInput[3]).negate(),
                new BigDecimal(parsedInput[4]).negate()
        );
    }

    /**
     * Checks the command for validity.
     *
     * @throws WrongArgumentLengthException warns of wrong argument length (i.e. count)
     * @throws NotANumberException          warns of not-a-number argument.
     */
    public void check() throws WrongArgumentLengthException, NotANumberException {
        if (this.parsedInput.length != 5) {
            throw new WrongArgumentLengthException(template);
        }

        for (int i = 1; i < 5; i++) {
            if (isNotNumber(parsedInput[i])) {
                throw new NotANumberException(this.parsedInput[i], template);
            }
        }
    }
}
