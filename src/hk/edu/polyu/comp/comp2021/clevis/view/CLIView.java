package hk.edu.polyu.comp.comp2021.clevis.view;

import hk.edu.polyu.comp.comp2021.clevis.Config;
import hk.edu.polyu.comp.comp2021.clevis.controller.CommandHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

        copyright();
    }

    /**
     * The main loop for CLI view.
     */
    public void exec() {
        String s;
        try {
            while ((s = in.readLine()) != null) {
                if (!s.isEmpty()) {
                    // Call command
                    handler.exec(s);

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
                    System.out.print(Config.CLI_COLOUR_INPUT);
                    System.out.println();


                    // Auto-quit
                    if (handler.getQuitting()) {
                        // TODO: Should we add good-bye message?

                        in.close();
                        return;
                    }
                }
            }
            in.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Prints the copyright to the output.
     */
    public void copyright() {
        // TODO: Print copyright
        // TODO: Add note to how to quit
    }
}
