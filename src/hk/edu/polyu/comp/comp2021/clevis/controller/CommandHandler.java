package hk.edu.polyu.comp.comp2021.clevis.controller;

import hk.edu.polyu.comp.comp2021.clevis.Config;
import hk.edu.polyu.comp.comp2021.clevis.controller.commands.*;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.NotANumberException;
import hk.edu.polyu.comp.comp2021.clevis.controller.exceptions.WrongArgumentLengthException;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private final ArrayList<Command> commandStack = new ArrayList<>();
    // Commands
    private String inString; // For exception warning
    private int commandCount = Config.HTML_INDEX_BEGIN; // for htmlOut only
    private int stackPtr = 0; // points at the location for NEXT input; == stack size when no undo
    // Outputs
    private ArrayList<String> outString = null; // for output by CLI or GUI message box(es)
    private boolean warning = false; // if the output is a warning (important for GUI)
    private boolean quitting = false; // whether the program is to quit or not, for Application
    // Logs
    private BufferedWriter htmlOut;
    private BufferedWriter txtOut;

    /**
     * Creates a command handler.
     *
     * @param model    the model to run commands
     * @param htmlFile the html file to log commands
     * @param txtFile  the txt file to log commands
     */
    public CommandHandler(Clevis model, File htmlFile, File txtFile) {
        this.model = model;

        try {
            // Init writers
            this.htmlOut = new BufferedWriter(new FileWriter(htmlFile));
            this.txtOut = new BufferedWriter(new FileWriter(txtFile));

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Config.HTML_DATE_TIME_FORMAT);
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
            htmlOut.write("    <h1>CLEVIS LOG</h1>");
            htmlOut.newLine();
            htmlOut.write("    <h3> <tt> Log start at " + dateTimeFormatter.format(now) + " </tt> </h3>");
            htmlOut.newLine();
            htmlOut.write("    <table style=\"border:1px #FFD382 dashed;\" cellpadding=\"10\" border='1'>");
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
        }
    }

    /**
     * Executes the given command.
     *
     * @param command A string of user-input command
     */
    public void exec(String command) {
        Command object;

        inString = command.replaceAll("^\\s+", "").replaceAll("\\s+", " ");

        try {
            // Store in txt
            txtOut.write(inString);
            txtOut.newLine();

            // Store in html
            htmlOut.write("      <tr>");
            htmlOut.newLine();
            htmlOut.write("        <td bgcolor=#ACBBFE>" + commandCount++ + "</td>");
            htmlOut.newLine();
            htmlOut.write("        <td bgcolor=#cfd5ea><tt>" + inString + "</tt></td>");
            htmlOut.newLine();
            htmlOut.write("      </tr>");
            htmlOut.newLine();
        } catch (IOException e) {
            handleIOException();
            return;
        }

        // Set outString to empty
        outString = new ArrayList<>();

        // Create command object / handle special cases
        try {
            switch (inString.split(" ", 0)[0]) {
                // Shapes
                case "line":
                    object = new CommandLine(model, inString);
                    break;
                case "circle":
                    object = new CommandCircle(model, inString);
                    break;
                case "rectangle":
                    object = new CommandRectangle(model, inString);
                    break;
                case "square":
                    object = new CommandSquare(model, inString);
                    break;

                // Groups
                case "group":
                    object = new CommandGroup(model, inString);
                    break;
                case "ungroup":
                    object = new CommandUngroup(model, inString);
                    break;

                // Info
                case "boundingbox":
                    object = new CommandBoundingBox(model, inString);
                    break;
                case "intersect":
                    object = new CommandIntersect(model, inString);
                    break;
                case "list":
                    object = new CommandList(model, inString);
                    break;
                case "listAll":
                    object = new CommandListAll(model, inString);
                    break;

                // Change
                case "move":
                    object = new CommandMove(model, inString);
                    break;
                case "pick-and-move":
                    object = new CommandPickAndMove(model, inString);
                    break;
                case "delete":
                    object = new CommandDelete(model, inString);
                    break;

                // Special cases
                case "help":
                    help();
                    return;
                case "info":
                    info();
                    return;
                case "undo":
                    undo();
                    return;
                case "redo":
                    redo();
                    return;
                case "quit":
                    quit();
                    return;
                case "exit":
                    exit();
                    return;

                // Command not found
                default:
                    handleNoCommandFound(inString.split(" ", 0)[0]);
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
        } catch (NoShapeContainsPointException e) {
            handleNoShapeContainsPointException();
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
        } catch (NegativeSizeException e) {
            handleNegativeSizeException();
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

    /**
     * Handles undo command.
     */
    public void undo() {
        // Check if there is no command to undo
        if (commandStack.size() == 0 || stackPtr == 0) {
            handleNoUndo();
            return;
        }

        try {
            warning = false;
            outString = new ArrayList<>();

            commandStack.get(--stackPtr).undo();
        } catch (ShapeInsideGroupException e) {
            handleShapeInsideGroupException(e);
        } catch (EmptyGroupException e) {
            handleEmptyGroupException();
        } catch (ShapeNotFoundException e) {
            handleShapeNotFoundException(e);
        } catch (DuplicateShapeNameException e) {
            handleDuplicateShapeNameException(e);
        }
    }

    /**
     * Handles redo command.
     */
    public void redo() {
        // Check if there is no command to redo
        if (stackPtr == commandStack.size()) {
            handleNoRedo();
            return;
        }

        try {
            warning = false;
            outString = new ArrayList<>();

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
        } catch (NegativeSizeException e) {
            handleNegativeSizeException();
        }
    }

    /**
     * Handles quit command (and auto-quit).
     */
    public void quit() {
        try {
            // Closing boilerplate for html
            htmlOut.write("    </table>");
            htmlOut.newLine();
            htmlOut.write("    <footer>");
            htmlOut.newLine();
            htmlOut.write("      <p> <tt>clevis</tt> by Group 32 </p>");
            htmlOut.newLine();
            htmlOut.write("      <p> COMP2021, Autumn 2021 </p>");
            htmlOut.newLine();
            htmlOut.write("      <p> The Hong Kong Polytechnic University </p>");
            htmlOut.newLine();
            htmlOut.write("    </footer>");
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

    /**
     * Handles "exit", which is deprecated (i.e. invalid).
     */
    public void exit() {
        outString = new ArrayList<>();

        outString.add("\"exit\" is deprecated.");
        outString.add("Please use \"quit\" to quit.");

        // Set flags
        warning = true;
    }

    /**
     * Gives help.
     */
    public void help() {
        outString = new ArrayList<>();

        outString.add("To execute a command, type its name and arguments (if any), then hit enter");
        outString.add("");
        outString.add("To use GUI, restart the program with the flag \"-gui\"");
        outString.add("");
        outString.add("List of commands:");
        outString.add("Shapes: rectangle, line, circle, square");
        outString.add("Groups: group, ungroup");
        outString.add("  Info: boundingbox, intersect, list, listAll");
        outString.add("Change: move, pick-and-move, delete");
        outString.add("  Meta: undo, redo, quit, help, info");
        outString.add("");
        outString.add("To find a command's argument list, execute that command and read the resultant warning (if any)");
    }

    /**
     * Gives info.
     */
    public void info() {
        outString = new ArrayList<>();

        outString.add("Clevis: Command Line Vector Graphics Software");
        outString.add("");
        outString.add("Authors:");
        outString.add("Max Ho, Joseph Mok, Nathan Chui");
        outString.add("Group 32, COMP2021");
        outString.add("The Polytechnic University of Hong Kong");
    }

    /**
     * Handles no command found exception.
     *
     * @param wrongInput the wrong command
     */
    public void handleNoCommandFound(String wrongInput) {
        // Warn of no command found nicely
        outString = new ArrayList<>();

        outString.add("\"" + wrongInput + "\" is not a command!");
        outString.add("");
        outString.add("You inputted: " + inString);
        outString.add("");
        outString.add("List of commands:");
        outString.add("Shapes: rectangle, line, circle, square");
        outString.add("Groups: group, ungroup");
        outString.add("  Info: boundingbox, intersect, list, listAll");
        outString.add("Change: move, pick-and-move, delete");
        outString.add("  Meta: undo, redo, quit, help, info");
        outString.add("");
        outString.add("To see this list, enter \"help\"");

        warning = true;
    }

    /**
     * Handles no undo exception.
     */
    public void handleNoUndo() {
        // Warn of no undo nicely
        outString = new ArrayList<>();

        outString.add("There are no commands to undo!");

        warning = true;
    }

    /**
     * Handles no redo exception.
     */
    public void handleNoRedo() {
        // Warn of no redo nicely
        outString = new ArrayList<>();

        outString.add("There are no commands to redo!");

        warning = true;
    }

    /**
     * Handles IOException.
     */
    public void handleIOException() {
        // Warn of invalid file nicely
        outString = new ArrayList<>();

        outString.add("The file path(s) you gave cannot be written to.");
        outString.add("It may mean that you gave path(s) to directory(ies) instead.");
        outString.add("We advise you to double-check your paths,");
        outString.add("then re-init the program with the proper paths.");

        warning = true;
    }

    /**
     * Handles not a number exception.
     *
     * @param e the exception
     */
    public void handleNotANumberException(NotANumberException e) {
        // Warn of not-a-number input nicely
        outString = new ArrayList<>();

        outString.add("You have inputted one (or more) non-number inputs in number argument fields!");
        outString.add("");
        outString.add("     Argument template: " + e.getTemplate());
        outString.add("          You inputted: " + inString);
        outString.add("One of the wrong input: " + e.getWrongInput());

        warning = true;
    }

    /**
     * Handles wrong argument length exception.
     *
     * @param e the exception
     */
    public void handleWrongArgumentLengthException(WrongArgumentLengthException e) {
        // Warn of wrong argument length nicely
        outString = new ArrayList<>();

        outString.add("You have inputted the command with a wrong argument count!");
        outString.add("");
        outString.add("Argument template: " + e.getTemplate());
        outString.add("     You inputted: " + inString);
        outString.add("");
        outString.add("Expected argument count: " + (e.getTemplate().split(" ").length - 1));
        outString.add("  Actual argument count: " + (inString.split(" ", 0).length - 1));

        warning = true;
    }

    /**
     * Handles duplicate shape name exception.
     *
     * @param e the exception
     */
    public void handleDuplicateShapeNameException(DuplicateShapeNameException e) {
        // Warn of duplicate shape name nicely
        outString = new ArrayList<>();

        outString.add("The name you inputted had been used by another shape already!");
        outString.add("");
        outString.add("You inputted: " + inString);
        outString.add("    The name: " + e.getName());

        warning = true;
    }

    /**
     * Handles empty group exception.
     */
    public void handleEmptyGroupException() {
        // Warn of empty group nicely
        outString = new ArrayList<>();

        outString.add("You have tried to create an empty group, which is invalid!");
        outString.add("Please confirm that you have inputted shapes to be grouped.");
        outString.add("");
        outString.add("You inputted: " + inString);

        warning = true;
    }

    /**
     * Handles shape inside group exception.
     *
     * @param e the exception
     */
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

    /**
     * Handles shape not found exception.
     *
     * @param e the exception
     */
    public void handleShapeNotFoundException(ShapeNotFoundException e) {
        // Warn of shape not found nicely
        outString = new ArrayList<>();

        outString.add("One (or more) of the shapes you inputted cannot be found!");
        outString.add("");
        outString.add("               You inputted: " + inString);
        outString.add("One of the shapes not found: " + e.getName());

        warning = true;
    }

    /**
     * Handles size is zero exception.
     */
    public void handleSizeIsZeroException() {
        // Warn of size is zero nicely
        outString = new ArrayList<>();

        outString.add("The shape you are trying to create has zero area!");
        outString.add("");
        outString.add("You inputted: " + inString);

        warning = true;
    }

    /**
     * Handles no shape contains point exception.
     */
    public void handleNoShapeContainsPointException() {
        // Warn of no shape contains point nicely
        outString = new ArrayList<>();

        outString.add("No shape contains the specified point!");
        outString.add("");
        outString.add("You inputted: " + inString);

        warning = true;
    }

    /**
     * Handles size is negative exception.
     */
    public void handleNegativeSizeException() {
        // Warn of size is zero nicely
        outString = new ArrayList<>();

        outString.add("The shape you are trying to create has negative size!");
        outString.add("");
        outString.add("You inputted: " + inString);

        warning = true;
    }

    /**
     * Gets the output strings.
     *
     * @return the output
     */
    public ArrayList<String> getOutString() {
        return this.outString;
    }

    /**
     * Gets whether the output is a warning.
     *
     * @return if the output is a warning
     */
    public boolean getWarning() {
        return this.warning;
    }

    /**
     * Gets whether the program is to quit.
     *
     * @return if the program is to quit
     */
    public boolean getQuitting() {
        return this.quitting;
    }
}
