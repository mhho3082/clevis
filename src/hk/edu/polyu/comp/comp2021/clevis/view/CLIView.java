package hk.edu.polyu.comp.comp2021.clevis.view;

import hk.edu.polyu.comp.comp2021.clevis.controller.CommandHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLIView {
    private final BufferedReader in;
    private final CommandHandler handler;

    public CLIView(CommandHandler handler) {
        this.in = new BufferedReader(new InputStreamReader(System.in));
        this.handler = handler;

        copyright();
    }

    public void exec() {
        String s;
        try {
            while ((s = in.readLine()) != null) {
                // Call command
                handler.exec(s);

                System.out.println();

                // Print output
                if (handler.getOutString() != null) {
                    // For the ANSI escape code for colours:
                    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

                    // Note:
                    // Colours may not certainly work for Windows
                    // Known not to work on cmd
                    // Seems to work on PowerShell and PowerShell Core

                    // Change output colour
                    // Add Warning sign
                    if (handler.getWarning()) {
                        System.out.print("\u001B[33m"); // Yellow
                        System.out.println("Warning:");
                    } else {
                        System.out.print("\u001B[34m"); // Blue
                    }

                    for (String out : handler.getOutString()) {
                        System.out.println(out);
                    }

                    // Set input colour
                    System.out.print("\u001B[0m"); // Reset colour
                    System.out.println();
                }

                // Auto-quit
                if (handler.getQuitting()) {
                    // TODO: Add good-bye message

                    in.close();
                    return;
                }
            }
            in.close();
        } catch (IOException ignored) {
        }
    }

    public void copyright() {
        // TODO: Print copyright
        // TODO: Add note to quit
    }
}
