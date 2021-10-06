package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CommandMove extends Command {
    // move name dx dy

    public CommandMove(Clevis model, String command) {
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

    public boolean check() {
        if (this.parsedInput.length != 4) {
            return false;
        }

        if (!this.parsedInput[0].equals("move")) {
            return false;
        }

        for (int i = 2; i < 4; i++) {
            if (isNotNumber(this.parsedInput[i])) {
                return false;
            }
        }

        return true;
    }

    public boolean undoable() {
        return true;
    }
}
