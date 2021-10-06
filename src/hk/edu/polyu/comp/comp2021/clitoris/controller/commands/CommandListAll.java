package hk.edu.polyu.comp.comp2021.clitoris.controller.commands;

import hk.edu.polyu.comp.comp2021.clitoris.model.Clitoris;

import java.util.ArrayList;

public class CommandListAll extends Command {
    // listAll

    public CommandListAll(Clitoris model, String command) {
        super(model, command);
    }

    public ArrayList<String> exec() {
        return model.listAll();
    }

    public void undo() {
    }

    public boolean check() {
        if (this.parsedInput.length != 1) {
            return false;
        }

        return this.parsedInput[0].equals("listAll");
    }

    public boolean undoable() {
        return false;
    }
}
