package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandBoundingBox extends Command {
    // boundingbox name

    public CommandBoundingBox(Clevis model, String command) {
        super(model, command);
    }

    public ArrayList<String> exec() throws ShapeInsideGroupException, ShapeNotFoundException {
        BigDecimal[] tempOut = model.boundingBox(parsedInput[1]);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            result.append(Math.round(tempOut[i].doubleValue() * 100.0) / 100.0);
            if (i != 3) {
                result.append(" ");
            }
        }

        ArrayList<String> temp = new ArrayList<>();
        temp.add(result.toString());
        return temp;
    }

    public void undo() {
    }

    public boolean check() {
        if (this.parsedInput.length != 2) {
            return false;
        }

        return this.parsedInput[0].equals("boundingbox");
    }

    public boolean undoable() {
        return false;
    }
}
