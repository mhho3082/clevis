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
    private int commandCount = 0; // for htmlOut only
    private final ArrayList<Command> commandStack = new ArrayList<>();
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
            htmlOut.write("    <tr>");
            htmlOut.newLine();
            htmlOut.write("      <th>Index</th>");
            htmlOut.newLine();
            htmlOut.write("      <th>Command</th>");
            htmlOut.newLine();
            htmlOut.write("    </tr>");
            htmlOut.newLine();

        } catch (IOException e) {
            // TODO: Warn of invalid file input nicely, and quit
            e.printStackTrace();
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
            e.printStackTrace();
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
        } catch (WrongArgumentLengthException e) { // TODO: Handle this exception nicely
            e.printStackTrace();
            warning = true;
            return;
        } catch (NotANumberException e) {
            e.printStackTrace();
            warning = true;
            return;
        } catch (ShapeNotFoundException e) {
            e.printStackTrace();
            warning = true;
            return;
        }

        // exec command object
        try {
            warning = false;
            outString = object.exec(); // TODO: handle exceptions nicely
        } catch (ShapeInsideGroupException e) {
            e.printStackTrace();
            warning = true;
            return;
        } catch (ShapeNotFoundException e) {
            e.printStackTrace();
            warning = true;
            return;
        } catch (DuplicateShapeNameException e) {
            e.printStackTrace();
            warning = true;
            return;
        } catch (SizeIsZeroException e) {
            e.printStackTrace();
            warning = true;
            return;
        } catch (EmptyGroupException e) {
            e.printStackTrace();
            warning = true;
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
            // TODO: handle "no command to undo" nicely
            warning = true;
            return;
        }

        try {
            commandStack.get(--stackPtr).undo(); // TODO: handle exceptions nicely
        } catch (ShapeInsideGroupException e) {
            e.printStackTrace();
            warning = true;
        } catch (EmptyGroupException e) {
            e.printStackTrace();
            warning = true;
        } catch (ShapeNotFoundException e) {
            e.printStackTrace();
            warning = true;
        } catch (DuplicateShapeNameException e) {
            e.printStackTrace();
            warning = true;
        }
    }

    public void redo() {
        if (stackPtr == commandStack.size()) {
            // no command to redo
            // TODO: handle "no command to redo" nicely
            warning = true;
            return;
        }

        try {
            commandStack.get(stackPtr++).exec(); // TODO: handle exceptions nicely
        } catch (ShapeInsideGroupException e) {
            e.printStackTrace();
            warning = true;
        } catch (ShapeNotFoundException e) {
            e.printStackTrace();
            warning = true;
        } catch (DuplicateShapeNameException e) {
            e.printStackTrace();
            warning = true;
        } catch (SizeIsZeroException e) {
            e.printStackTrace();
            warning = true;
        } catch (EmptyGroupException e) {
            e.printStackTrace();
            warning = true;
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        quitting = true;
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
