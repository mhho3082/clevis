package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CommandPickAndMove extends Command {
    // pick-and-move x y dx dy

    public CommandPickAndMove(Clevis model, String command) {
        super(model, command);
    }

    public ArrayList<String> exec() throws ShapeNotFoundException {
        model.pickAndMove(
                new BigDecimal(parsedInput[1]),
                new BigDecimal(parsedInput[2]),
                new BigDecimal(parsedInput[3]),
                new BigDecimal(parsedInput[4])
        );

        return null;
    }

    public void undo() throws ShapeNotFoundException {
        model.pickAndMove(
                new BigDecimal(parsedInput[1]),
                new BigDecimal(parsedInput[2]),
                new BigDecimal(parsedInput[3]).negate(),
                new BigDecimal(parsedInput[4]).negate()
        );
    }

    public boolean check() {
        if (this.parsedInput.length != 5) {
            return false;
        }

        if (!this.parsedInput[0].equals("pick-and-move")) {
            return false;
        }

        for (int i = 1; i < 5; i++) {
            if (isNotNumber(parsedInput[i])) {
                return false;
            }
        }

        return true;
    }

    public boolean undoable() {
        return true;
    }
}
