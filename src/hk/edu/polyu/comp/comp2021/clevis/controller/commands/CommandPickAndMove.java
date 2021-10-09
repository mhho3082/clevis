package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.NoShapeContainsPointException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;
import hk.edu.polyu.comp.comp2021.clevis.model.shapes.UserShape;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A command to call for "pick-and-move".
 *
 * @author Ho Man Hin
 */
public class CommandPickAndMove extends Command {
    private static final String template = "pick-and-move x y dx dy";

    private final UserShape shape;

    /**
     * Creates a command calling pick() and move().
     *
     * @param model   the model to call command to
     * @param command the command to call
     * @throws WrongArgumentLengthException  warns of wrong argument length
     * @throws NotANumberException           warns of not-a-number arguments
     * @throws NoShapeContainsPointException warns of no shape containing point
     */
    public CommandPickAndMove(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException, NoShapeContainsPointException {
        super(model, command);
        shape = model.pick(new BigDecimal(parsedInput[1]), new BigDecimal(parsedInput[2]));
    }

    /**
     * Executes the command.
     *
     * @return empty output
     * @throws ShapeNotFoundException    warns of one (or more) of shape not found
     * @throws ShapeInsideGroupException warns of shape inside group
     */
    @Override
    public ArrayList<String> exec() throws ShapeNotFoundException, ShapeInsideGroupException {
        model.move(
                shape.getName(),
                new BigDecimal(parsedInput[3]),
                new BigDecimal(parsedInput[4])
        );

        return new ArrayList<>();
    }

    /**
     * Undo the command.
     *
     * @throws ShapeNotFoundException    warns of one (or more) of shape not found
     * @throws ShapeInsideGroupException warns of shape inside group
     */
    @Override
    public void undo() throws ShapeNotFoundException, ShapeInsideGroupException {
        model.move(
                shape.getName(),
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
    @Override
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
