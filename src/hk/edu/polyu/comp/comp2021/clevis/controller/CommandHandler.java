package hk.edu.polyu.comp.comp2021.clevis.controller;

import hk.edu.polyu.comp.comp2021.clevis.controller.commands.*;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
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
    // The model
    private final Clevis model;
    // Commands
    private final ArrayList<Command> commandStack = new ArrayList<>();
    private int commandCount = 0; // for htmlOut only
    private int stackPtr = 0; // points at the location for NEXT input; == stack size when no undo
    // Outputs
    private ArrayList<String> outString; // for output by CLI or GUI message box(es)
    private boolean warning = false; // if the output is a warning (important for GUI)
    private boolean quitting = false; // if the program is to quit or not, for Application
    // Logs
    private BufferedWriter htmlOut;
    private BufferedWriter txtOut;

    public CommandHandler(Clevis model, File htmlFile, File txtFile) {
        this.model = model;

        try {
            // Init writers
            this.htmlOut = new BufferedWriter(new FileWriter(htmlFile));
            this.txtOut = new BufferedWriter(new FileWriter(txtFile));

            // Opening boilerplate for html
            htmlOut.write("<!DOCTYPE html>");
            htmlOut.newLine();
            htmlOut.write("<head>");
            htmlOut.newLine();
            htmlOut.write("  <title>Clevis Log</title>");
            htmlOut.newLine();
            htmlOut.write("</head>");
            htmlOut.newLine();
            htmlOut.write("<body>");
            htmlOut.newLine();
            htmlOut.write("  <table>");
            htmlOut.newLine();
            htmlOut.write("    <tr>");
            htmlOut.newLine();
            htmlOut.write("      <th>Index</th>");
            htmlOut.newLine();
            htmlOut.write("      <th>Command</th>");
            htmlOut.newLine();
            htmlOut.write("    </tr>");
            htmlOut.newLine();
        } catch (IOException e) {
            handleIOException(e);
            quit();
        }
    }

    public void exec(String command) {
        Command object;

        try {
            // Store in txt
            txtOut.write(command);
            txtOut.newLine();

            // Store in html
            htmlOut.write("    <tr>");
            htmlOut.newLine();
            htmlOut.write("      <td>" + commandCount++ + "</td>");
            htmlOut.newLine();
            htmlOut.write("      <td>" + command + "</td>");
            htmlOut.newLine();
            htmlOut.write("    </tr>");
            htmlOut.newLine();
        } catch (IOException e) {
            return;
        }

        try {
            switch (command.split(" ")[0]) {
                // Shapes
                case "line":
                    object = new CommandLine(model, command);
                    break;
                case "circle":
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
                    object = new CommandUngroup(model, command);
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
                case "undo":
                    undo();
                    return;
                case "redo":
                    redo();
                    return;
                case "quit":
                    quit();
                    return;

                // Command not found
                default:
                    throw new IllegalStateException("Unexpected value: " + command.split(" ")[0]);  // TODO: Handle "no command found" exception nicely
            }
        } catch (WrongArgumentLengthException e) {
            handleWrongArgumentLengthException(e);
            return;
        } catch (NotANumberException e) {
            handleNotANumberException(e);
            return;
        } catch (ShapeNotFoundException e) {
            handleShapeNotFoundException(e);
            return;
        }

        // exec command object
        try {
            warning = false;
            outString = object.exec();
        } catch (ShapeInsideGroupException e) {
            handleShapeInsideGroupException(e);
            return;
        } catch (ShapeNotFoundException e) {
            handleShapeNotFoundException(e);
            return;
        } catch (DuplicateShapeNameException e) {
            handleDuplicateShapeNameException(e);
            return;
        } catch (SizeIsZeroException e) {
            handleSizeIsZeroException(e);
            return;
        } catch (EmptyGroupException e) {
            handleEmptyGroupException(e);
            return;
        }

        // Put in stack if undoable
        if (object.undoable()) {
            // Pop all commands on old branch away, if any
            while (stackPtr != commandStack.size()) {
                commandStack.remove(commandStack.size() - 1);
            }

            commandStack.add(object);

            stackPtr++;
        }
    }

    public void undo() {
        if (commandStack.size() == 0 || stackPtr == 0) {
            // no command to undo
            // TODO: Warn of no command to undo nicely
            warning = true;
            return;
        }

        try {
            warning = false;
            commandStack.get(--stackPtr).undo();
        } catch (ShapeInsideGroupException e) {
            handleShapeInsideGroupException(e);
        } catch (EmptyGroupException e) {
            handleEmptyGroupException(e);
        } catch (ShapeNotFoundException e) {
            handleShapeNotFoundException(e);
        } catch (DuplicateShapeNameException e) {
            handleDuplicateShapeNameException(e);
        }
    }

    public void redo() {
        if (stackPtr == commandStack.size()) {
            // no command to redo
            // TODO: Warn of no command to redo nicely
            warning = true;
            return;
        }

        try {
            warning = false;
            commandStack.get(stackPtr++).exec();
        } catch (ShapeInsideGroupException e) {
            handleShapeInsideGroupException(e);
        } catch (ShapeNotFoundException e) {
            handleShapeNotFoundException(e);
        } catch (DuplicateShapeNameException e) {
            handleDuplicateShapeNameException(e);
        } catch (SizeIsZeroException e) {
            handleSizeIsZeroException(e);
        } catch (EmptyGroupException e) {
            handleEmptyGroupException(e);
        }
    }

    public void quit() {
        try {
            // Closing boilerplate for html
            htmlOut.write("  </table>");
            htmlOut.newLine();
            htmlOut.write("</body>");
            htmlOut.newLine();
            htmlOut.write("</html>");
            htmlOut.newLine();

            // Close writers
            htmlOut.close();
            txtOut.close();
        } catch (IOException ignored) {}

        warning = false;
        quitting = true;
    }

    public void handleIOException(IOException e) {
        // TODO: Warn of invalid file nicely
        warning = true;
    }

    public void handleNotANumberException(NotANumberException e) {
        // TODO: Warn of not-a-number input nicely
        warning = true;
    }

    public void handleWrongArgumentLengthException(WrongArgumentLengthException e) {
        // TODO: Warn of wrong argument length nicely
        warning = true;
    }

    public void handleDuplicateShapeNameException(DuplicateShapeNameException e) {
        // TODO: Warn of duplicate shape name nicely
        warning = true;
    }

    public void handleEmptyGroupException(EmptyGroupException e) {
        // TODO: Warn of empty group nicely
        warning = true;
    }

    public void handleShapeInsideGroupException(ShapeInsideGroupException e) {
        // TODO: Warn of shape inside group nicely
        warning = true;
    }

    public void handleShapeNotFoundException(ShapeNotFoundException e) {
        // TODO: Warn of shape not found nicely
        warning = true;
    }

    public void handleSizeIsZeroException(SizeIsZeroException e) {
        // TODO: Warn of size is zero nicely
        warning = true;
    }

    public ArrayList<String> getOutString() {
        return this.outString;
    }

    public boolean getWarning() {
        return this.warning;
    }

    public boolean getQuitting() {
        return this.quitting;
    }
}
