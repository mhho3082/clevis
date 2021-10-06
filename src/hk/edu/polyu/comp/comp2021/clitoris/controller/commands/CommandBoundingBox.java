package hk.edu.polyu.comp.comp2021.clitoris.controller.commands;

import hk.edu.polyu.comp.comp2021.clitoris.model.Clitoris;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.ShapeNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandBoundingBox extends Command {
    // boundingbox name

    public CommandBoundingBox(Clitoris model, String command) {
        super(model, command);
    }

    public ArrayList<String> exec() throws ShapeInsideGroupException, ShapeNotFoundException {
        String result = Arrays.toString(model.boundingBox(parsedInput[1]));

        ArrayList<String> temp = new ArrayList<>();
        temp.add(result);
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
