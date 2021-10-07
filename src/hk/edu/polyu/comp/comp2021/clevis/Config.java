package hk.edu.polyu.comp.comp2021.clevis;

/**
 * The settings for the code to function.
 * Please manually change for now.
 * Will try to give settings functions later.
 *
 * @author Ho Man Hin
 */
public class Config {
    // Indentation for list and listAll
    public static final String INDENT = "   |"; // 4 spaces

    // Sentence to announce command completed
    public static final String CLI_AOK = "Done";

    // Sentence to begin warning message
    public static final String CLI_WARNING_BEGIN = "Warning:";

    // For the ANSI escape code for colours:
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    // Note:
    // Colours may not certainly work for Windows
    // Known not to work on cmd
    // Seems to work on PowerShell and PowerShell Core

    // Colour for normal CLI output
    public static final String CLI_COLOUR_NORMAL = "\u001B[34m"; // Blue

    // Colour for warning CLI output
    public static final String CLI_COLOUR_WARNING = "\u001B[33m"; // Yellow

    // Colour for CLI input
    public static final String CLI_COLOUR_INPUT = "\u001B[0m"; // Reset
}
