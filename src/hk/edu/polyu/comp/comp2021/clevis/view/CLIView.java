package hk.edu.polyu.comp.comp2021.clevis.view;

import hk.edu.polyu.comp.comp2021.clevis.Config;
import hk.edu.polyu.comp.comp2021.clevis.controller.CommandHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The view for CLI.
 *
 * @author Ho Man Hin
 */
public class CLIView {
    private final BufferedReader in;
    private final CommandHandler handler;

    /**
     * Creates a CLI view.
     *
     * @param handler the command handler for user commands
     */
    public CLIView(CommandHandler handler) {
        this.in = new BufferedReader(new InputStreamReader(System.in));
        this.handler = handler;
    }

    /**
     * The main loop for CLI view.
     */
    public void exec() {
        String s;

        beginMessage();

        try {
            System.out.print(Config.CLI_INPUT_ICON);
            while ((s = in.readLine()) != null) {
                if (!s.isEmpty()) {
                    // Call command
                    handler.exec(s);

                    // Auto-quit
                    if (handler.getQuitting()) {
                        in.close();
                        return;
                    }

                    System.out.println();

                    // Change output colour
                    // Add Warning sign
                    if (handler.getWarning()) {
                        System.out.print(Config.CLI_COLOUR_WARNING);
                        System.out.println(Config.CLI_WARNING_BEGIN);
                    } else {
                        System.out.print(Config.CLI_COLOUR_NORMAL);
                    }

                    // Print output
                    if (handler.getOutString() != null) {
                        for (String out : handler.getOutString()) {
                            System.out.println(out);
                        }
                    } else if (!handler.getQuitting()) {
                        System.out.println(Config.CLI_AOK);
                    }

                    // Set input colour
                    System.out.println(Config.CLI_COLOUR_INPUT);
                    System.out.print(Config.CLI_INPUT_ICON);
                }
            }
            in.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Prints the copyright to the output.
     */
    public void beginMessage() {
        System.out.print(Config.CLI_COLOUR_NORMAL);
        System.out.println("Clevis");
        System.out.println("Group 32, COMP2021, The Polytechnic University of Hong Kong");
        System.out.println("Enter \"help\" or \"help [command]\" for help");
        System.out.println("Enter \"quit\" to quit");
        System.out.println(Config.CLI_COLOUR_INPUT);
    }
}
