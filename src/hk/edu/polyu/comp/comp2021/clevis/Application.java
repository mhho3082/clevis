package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.controller.CommandHandler;
import hk.edu.polyu.comp.comp2021.clevis.controller.PlotHandler;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.view.CLIView;
import hk.edu.polyu.comp.comp2021.clevis.view.GUIView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * The main class for the whole project.
 * The main class for .jar files.
 *
 * @author Ho Man Hin
 */
public class Application {
    private static final String[] arguments = {
            "-html", // -html file : for html log
            "-txt", // -txt file : for txt log
            "-gui" // -gui : for GUI
    };

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

        // Handle command line arguments
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-html")
                    && i + 1 < args.length
                    && !Arrays.asList(arguments).contains(args[i + 1])
            ) {
                html = new File(args[i + 1]);
            } else if (args[i].equals("-txt")
                    && i + 1 < args.length
                    && !Arrays.asList(arguments).contains(args[i + 1])
            ) {
                txt = new File(args[i + 1]);
            } else if (args[i].equals("-gui")) {
                useGUI = true;
            }
        }

        // Check that both paths are given and have the appropriate extensions
        if (html == null || txt == null
                || !("." + html).substring(("." + html).lastIndexOf('.')).equals(".html")
                || !("." + txt).substring(("." + txt).lastIndexOf('.')).equals(".txt")) {
            handleLackOfPaths();
            return;
        }

        // Check that the paths can be written to
        try {
            new FileWriter(html).close();
            new FileWriter(txt).close();
        } catch (IOException e) {
            handleBadPath();
            return;
        }

        // Initialize and utilize the system
        Clevis clevis = new Clevis();
        CommandHandler commandHandler = new CommandHandler(clevis, html, txt);
        if (useGUI) {
            PlotHandler plotHandler = new PlotHandler(clevis);
            GUIView guiView = new GUIView(commandHandler, plotHandler);
            guiView.launchFrame();
        } else {
            CLIView cliView = new CLIView(commandHandler);
            cliView.launch();
        }
    }

    private static void handleLackOfPaths() {
        System.out.print(Config.CLI_COLOUR_ERROR);
        System.out.println(Config.CLI_ERROR_BEGIN);
        System.out.println("You have not gave paths to log files in html and/or txt formats.");
        System.out.println("Please make sure that you have gave both paths, then re-init the program.");
        System.out.println();
        System.out.println("Format:");
        System.out.println("java Clevis -html [html file name] -txt [txt file name]");
        System.out.print(Config.CLI_COLOUR_INPUT);
    }

    private static void handleBadPath() {
        System.out.print(Config.CLI_COLOUR_ERROR);
        System.out.println(Config.CLI_ERROR_BEGIN);
        System.out.println("The file path(s) you gave cannot be written to.");
        System.out.println("It may mean that you gave path(s) to directory(ies) instead.");
        System.out.println("Please double-check your paths,");
        System.out.println("then re-init the program with the proper paths.");
        System.out.println();
        System.out.println("Format:");
        System.out.println("java Clevis -html [html file name] -txt [txt file name]");
        System.out.print(Config.CLI_COLOUR_INPUT);
    }
}
