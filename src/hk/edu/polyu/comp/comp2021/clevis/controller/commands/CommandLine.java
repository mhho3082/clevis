package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CommandLine extends Command {
    // line name x1 x2 y1 y2

    public CommandLine(Clevis model, String command) {
        super(model, command);
    }

    public ArrayList<String> exec()
            throws SizeIsZeroException, DuplicateShapeNameException {
        model.addLine(
                this.parsedInput[1],
                new BigDecimal(this.parsedInput[2]),
                new BigDecimal(this.parsedInput[3]),
                new BigDecimal(this.parsedInput[4]),
                new BigDecimal(this.parsedInput[5])
        );

        return null;
    }

    public void undo()
            throws ShapeInsideGroupException, ShapeNotFoundException {
        model.remove(this.parsedInput[1]);
    }

    public boolean check() {
        if (this.parsedInput.length != 6) {
            return false;
        }

        if (!this.parsedInput[0].equals("line")) {
            return false;
        }

        for (int i = 2; i < 6; i++) {
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
