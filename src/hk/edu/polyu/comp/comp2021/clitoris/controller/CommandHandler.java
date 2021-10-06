package hk.edu.polyu.comp.comp2021.clitoris.controller;

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
    private String inString;
    private ArrayList<String> outString;

    private final File htmlFile;
    private final File txtFile;

    private BufferedWriter htmlOut;
    private BufferedWriter txtOut;

    public CommandHandler(String html, String txt) {
        htmlFile = new File(html);
        txtFile = new File(txt);
    }

    public void initWriter() throws IOException {
        htmlOut = new BufferedWriter(new FileWriter(htmlFile));
        txtOut = new BufferedWriter(new FileWriter(txtFile));
    }

    // TODO: THIS! NOT EVEN BEGUN YET!

    public ArrayList<String> getOutString() {
        return this.outString;
    }
}
