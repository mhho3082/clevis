package hk.edu.polyu.comp.comp2021.clevis.controller;

import hk.edu.polyu.comp.comp2021.clevis.controller.commands.*;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.*;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

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
    private String inString; // For exception warning
    private final ArrayList<Command> commandStack = new ArrayList<>();
    private int commandCount = 0; // for htmlOut only
    private int stackPtr = 0; // points at the location for NEXT input; == stack size when no undo
    // Outputs
    private ArrayList<String> outString = null; // for output by CLI or GUI message box(es)
    private boolean warning = false; // if the output is a warning (important for GUI)
    private boolean quitting = false; // whether the program is to quit or not, for Application
    // Logs
    private BufferedWriter htmlOut;
    private BufferedWriter txtOut;

    public CommandHandler(Clevis model, File htmlFile, File txtFile) {
        this.model = model;

        try {
            // Init writers
            this.htmlOut = new BufferedWriter(new FileWriter(htmlFile));
            this.txtOut = new BufferedWriter(new FileWriter(txtFile));

            DateTimeFormatter dtfm = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now(); 

            // Opening boilerplate for html
            htmlOut.write("<!DOCTYPE html>");
            htmlOut.newLine();
            htmlOut.write("<html>");
            htmlOut.newLine();
            htmlOut.write("  <head>");
            htmlOut.newLine();
            htmlOut.write("    <title>Clevis Log</title>");
            htmlOut.newLine();
            htmlOut.write("  </head>");
            htmlOut.newLine();
            htmlOut.write("  <body>");
            htmlOut.newLine();
            htmlOut.write("<h1>GROUP 32 </h1>");
            htmlOut.newLine();
            htmlOut.write("<h3><i> ~Log start  < "+dtfm.format(now)+" > </i>~</h3>");
            htmlOut.newLine();
            htmlOut.write("    <table>");
            htmlOut.newLine();
            htmlOut.write("<table style=\"border:1px #FFD382 dashed;\" cellpadding=\"10\" border='1'>");
            htmlOut.newLine();
            htmlOut.write("      <tr>");
            htmlOut.newLine();
            htmlOut.write("        <th bgcolor=\"yellow\">Index</th>");
            htmlOut.newLine();
            htmlOut.write("        <th bgcolor=\"yellow\">Command</th>");
            htmlOut.newLine();
            htmlOut.write("      </tr>");
            htmlOut.newLine();
        } catch (IOException e) {
            handleIOException();
            quit();
        }
    }

    public void exec(String command) {
        Command object;

        inString = command;

        try {
            // Store in txt
            txtOut.write(command);
            txtOut.newLine();

            // Store in html
            htmlOut.write("      <tr>");
            htmlOut.newLine();
            htmlOut.write("        <td bgcolor=#ACBBFE>" + commandCount++ + "</td>");
            htmlOut.newLine();
            htmlOut.write("        <td bgcolor=#cfd5ea>" + command + "</td>");
            htmlOut.newLine();
            htmlOut.write("      </tr>");
            htmlOut.newLine();
        } catch (IOException e) {
            return;
        }

        // Set outString to empty
        outString = null;

        // Create command object / handle special cases
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
                    handleNoCommandFound(command.split(" ")[0]);
                    return;
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
            handleSizeIsZeroException();
            return;
        } catch (EmptyGroupException e) {
            handleEmptyGroupException();
            return;
        } catch (NoShapeContainsPointException e) {
            handleNoShapeContainsPointException();
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
        // Check if there is no command to undo
        if (commandStack.size() == 0 || stackPtr == 0) {
            handleNoUndo();
            return;
        }

        try {
            warning = false;
            outString = null;

            commandStack.get(--stackPtr).undo();
        } catch (ShapeInsideGroupException e) {
            handleShapeInsideGroupException(e);
        } catch (EmptyGroupException e) {
            handleEmptyGroupException();
        } catch (ShapeNotFoundException e) {
            handleShapeNotFoundException(e);
        } catch (DuplicateShapeNameException e) {
            handleDuplicateShapeNameException(e);
        } catch (NoShapeContainsPointException e) {
            handleNoShapeContainsPointException();
        }
    }

    public void redo() {
        // Check if there is no command to redo
        if (stackPtr == commandStack.size()) {
            handleNoRedo();
            return;
        }

        try {
            warning = false;
            outString = null;

            commandStack.get(stackPtr++).exec();
        } catch (ShapeInsideGroupException e) {
            handleShapeInsideGroupException(e);
        } catch (ShapeNotFoundException e) {
            handleShapeNotFoundException(e);
        } catch (DuplicateShapeNameException e) {
            handleDuplicateShapeNameException(e);
        } catch (SizeIsZeroException e) {
            handleSizeIsZeroException();
        } catch (EmptyGroupException e) {
            handleEmptyGroupException();
        } catch (NoShapeContainsPointException e) {
            handleNoShapeContainsPointException();
        }
    }

    public void quit() {
        try {
            // Closing boilerplate for html
            htmlOut.write("    </table>");
            htmlOut.newLine();
            htmlOut.write("  </body>");
            htmlOut.newLine();
            htmlOut.write("</html>");
            htmlOut.newLine();

            // Close writers
            htmlOut.close();
            txtOut.close();
        } catch (IOException ignored) {
        }

        // Set flags
        warning = false;
        quitting = true;
    }

    // TODO: Add help()

    // TODO: Add info()

    public void handleNoCommandFound(String wrongInput) {
        // Warn of no command found nicely
        outString = new ArrayList<>();

        outString.add(wrongInput + " is not a command!");
        outString.add("");
        outString.add("You inputted: " + inString);
        // TODO: Give correct command list

        warning = true;
    }

    public void handleNoUndo() {
        // Warn of no undo nicely
        outString = new ArrayList<>();

        outString.add("There are no commands to undo!");

        warning = true;
    }

    public void handleNoRedo() {
        // Warn of no redo nicely
        outString = new ArrayList<>();

        outString.add("There are no commands to redo!");

        warning = true;
    }

    public void handleIOException() {
        // Warn of invalid file nicely
        outString = new ArrayList<>();

        outString.add("The file path(s) you gave cannot be written to.");
        outString.add("It may mean that you gave path(s) to directory(ies) instead.");
        outString.add("Please double-check your paths, then re-init the program.");

        warning = true;
    }

    public void handleNotANumberException(NotANumberException e) {
        // Warn of not-a-number input nicely
        outString = new ArrayList<>();

        outString.add("You have inputted one (or more) non-number inputs in number argument fields!");
        outString.add("");
        outString.add("     Expected template: " + e.getTemplate());
        outString.add("          You inputted: " + inString);
        outString.add("One of the wrong input: " + e.getWrongInput());

        warning = true;
    }

    public void handleWrongArgumentLengthException(WrongArgumentLengthException e) {
        // Warn of wrong argument length nicely
        outString = new ArrayList<>();

        outString.add("You have inputted the command with a wrong argument count!");
        outString.add("");
        outString.add("Expected template: " + e.getTemplate());
        outString.add("     You inputted: " + inString);
        outString.add("");
        outString.add("Expected argument count: " + (e.getTemplate().split(" ").length - 1));
        outString.add("  Actual argument count: " + (inString.split(" ").length - 1));

        warning = true;
    }

    public void handleDuplicateShapeNameException(DuplicateShapeNameException e) {
        // Warn of duplicate shape name nicely
        outString = new ArrayList<>();

        outString.add("The name you inputted had been used by another shape already!");
        outString.add("");
        outString.add("You inputted: " + inString);
        outString.add("    The name: " + e.getName());

        warning = true;
    }

    public void handleEmptyGroupException() {
        // Warn of empty group nicely
        outString = new ArrayList<>();

        outString.add("You have tried to create an empty group, which is invalid!");
        outString.add("Please confirm that you have inputted shapes to be grouped.");
        outString.add("");
        outString.add("You inputted: " + inString);

        warning = true;
    }

    public void handleShapeInsideGroupException(ShapeInsideGroupException e) {
        // Warn of shape inside group nicely
        outString = new ArrayList<>();

        outString.add("One (or more) of the shapes you inputted is currently grouped!");
        outString.add("Shape(s) inside group(s) cannot be directly accessed.");
        outString.add("");
        outString.add("             You inputted: " + inString);
        outString.add("One of the grouped shapes: " + e.getShapeName());

        warning = true;
    }

    public void handleShapeNotFoundException(ShapeNotFoundException e) {
        // Warn of shape not found nicely
        outString = new ArrayList<>();

        outString.add("One (or more) of the shapes you inputted cannot be found!");
        outString.add("");
        outString.add("You inputted: " + inString);
        outString.add("One of the shapes not found: " + e.getName());

        warning = true;
    }

    public void handleSizeIsZeroException() {
        // Warn of size is zero nicely
        outString = new ArrayList<>();

        outString.add("The shape you are trying to create has zero area!");
        outString.add("");
        outString.add("You inputted: " + inString);

        warning = true;
    }

    public void handleNoShapeContainsPointException() {
        // Warn of no shape contains point nicely
        outString = new ArrayList<>();

        outString.add("No shape contains the specified point!");
        outString.add("");
        outString.add("You inputted: " + inString);

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
