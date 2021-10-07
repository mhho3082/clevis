package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.NoShapeContainsPointException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CommandPickAndMove extends Command {
    private static final String template = "pick-and-move x y dx dy";

    public CommandPickAndMove(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    public ArrayList<String> exec() throws ShapeNotFoundException, NoShapeContainsPointException {
        model.pickAndMove(
                new BigDecimal(parsedInput[1]),
                new BigDecimal(parsedInput[2]),
                new BigDecimal(parsedInput[3]),
                new BigDecimal(parsedInput[4])
        );

        return null;
    }

    public void undo() throws ShapeNotFoundException, NoShapeContainsPointException {
        model.pickAndMove(
                new BigDecimal(parsedInput[1]),
                new BigDecimal(parsedInput[2]),
                new BigDecimal(parsedInput[3]).negate(),
                new BigDecimal(parsedInput[4]).negate()
        );
    }

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
