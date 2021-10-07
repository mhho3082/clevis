package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CommandMove extends Command {
    private static final String template = "move name dx dy";

    public CommandMove(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
        super(model, command);
    }

    public ArrayList<String> exec()
            throws ShapeInsideGroupException, ShapeNotFoundException {
        model.move(parsedInput[1],
                new BigDecimal(parsedInput[2]),
                new BigDecimal(parsedInput[3]));

        return null;
    }

    public void undo()
            throws ShapeInsideGroupException, ShapeNotFoundException {
        model.move(parsedInput[1],
                new BigDecimal(parsedInput[2]).negate(),
                new BigDecimal(parsedInput[3]).negate()
        );
    }

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
