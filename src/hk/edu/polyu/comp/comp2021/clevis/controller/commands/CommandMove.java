package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A command to call for "move".
 *
 * @author Ho Man Hin
 */
public class CommandMove extends Command {
    private static final String template = "move name dx dy";

    /**
     * Creates a command calling move().
     *
     * @param model   the model to call command to
     * @param command the command to call
     * @throws WrongArgumentLengthException warns of wrong argument length
     * @throws NotANumberException          warns of not-a-number arguments
     */
    public CommandMove(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    /**
     * Calls the command for execution.
     *
     * @return empty output
     * @throws ShapeInsideGroupException warns of one (or more) of shape inside group
     * @throws ShapeNotFoundException    warns of one (or more) of shape not found
     */
    @Override
    public ArrayList<String> exec()
            throws ShapeInsideGroupException, ShapeNotFoundException {
        model.move(parsedInput[1],
                new BigDecimal(parsedInput[2]),
                new BigDecimal(parsedInput[3]));

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
        model.move(parsedInput[1],
                new BigDecimal(parsedInput[2]).negate(),
                new BigDecimal(parsedInput[3]).negate()
        );
    }

    /**
     * Checks the command for validity.
     *
     * @throws WrongArgumentLengthException warns of wrong argument length (i.e. count)
     * @throws NotANumberException          warns of not-a-number argument
     */
    @Override
    public void check() throws WrongArgumentLengthException, NotANumberException {
        if (this.parsedInput.length != 4) {
            throw new WrongArgumentLengthException(template);
        }

        for (int i = 2; i < 4; i++) {
            if (isNotNumber(this.parsedInput[i])) {
                throw new NotANumberException(this.parsedInput[i], template);
            }
        }
    }
}
