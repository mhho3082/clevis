package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.controller.CommandHandler;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.view.CLIView;

import java.io.File;

/**
 * The main class for the whole project.
 * The main class for .jar files.
 *
 * @author Ho Man Hin
 */
public class Application {
    /**
     * The main function for the whole project.
     *
     * @param args the CLI arguments, e.g. -html, -txt, -gui
     */
    public static void main(String[] args) {
        // Variables
        File html = null;
        File txt = null;
        boolean useGUI = false;

        // Handle flags
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-html")
                    && i + 1 < args.length
                    && !args[i + 1].equals("-html")
                    && !args[i + 1].equals("-txt")
                    && !args[i + 1].equals("-gui")
            ) {
                html = new File(args[i + 1]);
            } else if (args[i].equals("-txt")
                    && i + 1 < args.length
                    && !args[i + 1].equals("-html")
                    && !args[i + 1].equals("-txt")
                    && !args[i + 1].equals("-gui")
            ) {
                txt = new File(args[i + 1]);
            } else if (args[i].equals("-gui")) {
                useGUI = true;
            }
        }

        // Quit if invalid inputs
        if (html == null || txt == null) {
            // TODO: Warn of invalid inputs nicely with System.out, then halt
            return;
        }

        // Initialize and utilize the system
        // TODO: Add GUI controller and GUI view
        Clevis clevis = new Clevis();
        CommandHandler handler = new CommandHandler(clevis, html, txt);
        if (useGUI) {
        } else {
            CLIView cliView = new CLIView(handler);
            cliView.exec();
        }
    }
}
