package hk.edu.polyu.comp.comp2021.clevis.controller.commands;

import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.ShapeNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CommandBoundingBox extends Command {
    private static final String template =  "boundingbox name";

    public CommandBoundingBox(Clevis model, String command) throws WrongArgumentLengthException, NotANumberException {
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

    public void check() throws WrongArgumentLengthException {
        if (this.parsedInput.length != 2) {
            throw new WrongArgumentLengthException(this.input, template);
        }
    }

    public boolean undoable() {
        return false;
    }
}
