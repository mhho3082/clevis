package hk.edu.polyu.comp.comp2021.clitoris.controller.commands;

import hk.edu.polyu.comp.comp2021.clitoris.model.Clitoris;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.ShapeInsideGroupException;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.ShapeNotFoundException;

import java.util.ArrayList;

public class CommandIntersect extends Command {
    // intersect name1 name2

    public CommandIntersect(Clitoris model, String command) {
        super(model, command);
    }

    public ArrayList<String> exec()
            throws ShapeInsideGroupException, ShapeNotFoundException {
        String result = String.valueOf(model.intersect(parsedInput[1], parsedInput[2]));

        ArrayList<String> temp = new ArrayList<>();
        temp.add(result);
        return temp;
    }

    public void undo() {
    }

    public boolean check() {
        if (this.parsedInput.length != 3) {
            return false;
        }

        return this.parsedInput[0].equals("intersect");
    }

    public boolean undoable() {
        return false;
    }
}
