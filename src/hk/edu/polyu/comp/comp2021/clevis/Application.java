package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.controller.CommandHandler;
import hk.edu.polyu.comp.comp2021.clevis.model.*;

import java.io.File;

public class Application {
    public static void main(String[] args){
        // Variables
        File html = null;
        File txt = null;
        boolean useGUI = false;

        // Handle flags
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-html") && i + 1 < args.length) {
                html = new File(args[i+1]);
            } else if (args[i].equals("-txt") && i + 1 < args.length) {
                txt = new File(args[i+1]);
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
        handler.exec("rectangle hi 1 2 4 5");
        handler.exec("line yo 1 1 4 4");
        handler.exec("intersect hi yo");
        System.out.println(handler.getOutString());
        handler.exec("listAll");
        handler.exec("quit");
    }
}
