package hk.edu.polyu.comp.comp2021.clevis.controller;

import hk.edu.polyu.comp.comp2021.clevis.controller.commands.*;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles commands (e.g. undo and redo).
 * Stores called commands in logs.
 * Does not handle user input and output.
 *
 * @author Ho Man Hin
 */
public class CommandHandler {
    private Clevis model;

    private String inString;
    private ArrayList<String> outString;

    private BufferedWriter htmlOut;
    private BufferedWriter txtOut;

    private ArrayList<Command> commandStack;
    private int stackPtr = 0;
    // points at the location for next ptr
    // i.e. ptr == stack size for no-undo-ed stack

    public CommandHandler(Clevis model, File htmlFile, File txtFile) {
        this.model = model;

        try {
            this.htmlOut = new BufferedWriter(new FileWriter(htmlFile));
            this.txtOut = new BufferedWriter(new FileWriter(txtFile));
        } catch (IOException e) {
            // TODO: Warn of invalid file input nicely, and quit
            e.printStackTrace();
        }
    }

    public void exec(String command) {
        Command object = null;

        // TODO: Store command in logs

        switch(command.split(" ")[0]) {
            // Shapes
            case "line":
                object = new CommandLine(model, command);
                break;
            case "circle" :
                object = new CommandCircle(model, command);
                break;
            case "rectangle":
                object = new CommandRectangle(model, command);
                break;
            case "square":
                object = new CommandSquare(model, command);
                break;

            // Groups
            case "group":
                object = new CommandGroup(model, command);
                break;
            case "ungroup":
                try {
                    object = new CommandUngroup(model, command);
                } catch (ShapeNotFoundException e) {
                    e.printStackTrace(); // TODO: Handle this exception nicely
                }
                break;

            // Info
            case "boundingbox":
                object = new CommandBoundingBox(model, command);
                break;
            case "intersect":
                object = new CommandIntersect(model, command);
                break;
            case "list":
                object = new CommandList(model, command);
                break;
            case "listAll":
                object = new CommandListAll(model, command);
                break;

            // Change
            case "move":
                object = new CommandMove(model, command);
                break;
            case "pick-and-move":
                object = new CommandPickAndMove(model, command);
                break;
            case "delete":
                object = new CommandDelete(model, command);
                break;

            // Special cases
            // TODO: handle special cases properly
            case "undo":
                undo();
                return;
            case "redo":
                redo();
                return;
            case "quit":
                // ...
                return;

            // Command not found
            default:
                throw new IllegalStateException("Unexpected value: " + command.split(" ")[0]);  // TODO: Handle "no command found" exception nicely
        }

        if (object != null) {
            try {
                object.exec(); // TODO: handle exceptions nicely
            } catch (ShapeInsideGroupException e) {
                e.printStackTrace();
            } catch (ShapeNotFoundException e) {
                e.printStackTrace();
            } catch (DuplicateShapeNameException e) {
                e.printStackTrace();
            } catch (SizeIsZeroException e) {
                e.printStackTrace();
            } catch (EmptyGroupException e) {
                e.printStackTrace();
            }

            if (object.undoable()) {
                // Pop all commands on old branch away, if any
                while (stackPtr != commandStack.size()) {
                    commandStack.remove(commandStack.size() - 1);
                }

                commandStack.add(object);

                stackPtr++;
            }
        }
    }

    public void undo() {
        if (commandStack.size() == 0 || stackPtr == 0) {
            // no command to undo
            // TODO: handle "no command to undo" nicely
            return;
        }

        try {
            commandStack.get(--stackPtr).undo(); // TODO: handle exceptions nicely
        } catch (ShapeInsideGroupException e) {
            e.printStackTrace();
        } catch (EmptyGroupException e) {
            e.printStackTrace();
        } catch (ShapeNotFoundException e) {
            e.printStackTrace();
        } catch (DuplicateShapeNameException e) {
            e.printStackTrace();
        }
    }

    public void redo() {
        if (stackPtr == commandStack.size()) {
            // no command to redo
            // TODO: handle "no command to redo" nicely
            return;
        }

        try {
            commandStack.get(stackPtr++).exec(); // TODO: handle exceptions nicely
        } catch (ShapeInsideGroupException e) {
            e.printStackTrace();
        } catch (ShapeNotFoundException e) {
            e.printStackTrace();
        } catch (DuplicateShapeNameException e) {
            e.printStackTrace();
        } catch (SizeIsZeroException e) {
            e.printStackTrace();
        } catch (EmptyGroupException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getOutString() {
        return this.outString;
    }
}
