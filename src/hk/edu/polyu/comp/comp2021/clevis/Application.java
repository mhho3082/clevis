package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.controller.CommandHandler;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;

import java.io.File;

public class Application {
    public static void main(String[] args) {
        // Variables
        File html = null;
        File txt = null;
        boolean useGUI = false;

        // Handle flags
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-html") && i + 1 < args.length) {
                html = new File(args[i + 1]);
            } else if (args[i].equals("-txt") && i + 1 < args.length) {
                txt = new File(args[i + 1]);
            } else if (args[i].equals("-gui")) {
                useGUI = true;
            }
        }

        // Quit if invalid inputs
        if (html == null || txt == null) {
            // TODO: Halt if invalid inputs, warn with System.out
            return;
        }

        // Initialize and utilize the system
        // TODO: Add view
        Clevis clevis = new Clevis();
        CommandHandler handler = new CommandHandler(clevis, html, txt);

        // FIXME: For testing only
        // For Nathan: Feel free to do integration testing here
        // The concept for this code should be used in CLI view instead, to be honest...
        // The colours down there is set just for fun, feel free to change it
        String[] testCommandList = {
                "rectangle hi 1 2 4 5",
                "line yo 1 1 4 4",
                "intersect hi yo",
                "group x hi yo",
                "circle k 3 4 5",
                "list k",
                "undo",
                "listAll",
                "redo",
                "listAll",
                "quit",
        };
        for (String testCommand : testCommandList) {
            handler.exec(testCommand);
            if (handler.getOutString() != null) {
                // For the ANSI escape code for colours:
                // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

                if (handler.getWarning()) {
                    System.out.print("\u001B[33m"); // Yellow
                } else {
                    System.out.print("\u001B[34m"); // Blue
                }

                for (String out : handler.getOutString()) {
                    System.out.println(out);
                }

                System.out.print("\u001B[0m"); // Reset colour
                System.out.println();
            }
        }
    }
}
