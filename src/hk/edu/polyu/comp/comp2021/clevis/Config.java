package hk.edu.polyu.comp.comp2021.clevis;

/**
 * The settings for the code to function.
 * May be different for each user's preference.
 * Please manually change for now.
 * Will possibly try to give settings functions later.
 *
 * @author Ho Man Hin
 */
public class Config {
    // Indentation for list and listAll
    public static final String INDENT = "   |"; // 4 spaces

    // Index to begin html log with
    public static final int HTML_INDEX_BEGIN = 0;

    // HTML date & time pattern
    public static final String HTML_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    // Sentence to announce command completed
    public static final String CLI_AOK = "Done";

    // Sentence to begin warning message
    public static final String CLI_WARNING_BEGIN = "Warning:";

    // Sentence to begin error message
    public static final String CLI_ERROR_BEGIN = "Error:";

    // For the ANSI escape code for colours:
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    // Note:
    // Colours may not certainly work for Windows
    // Known not to work on cmd
    // Seems to work on PowerShell and PowerShell Core
    // Will work in VS Code and Intellij IDEA

    // Colour for normal CLI output
    public static final String CLI_COLOUR_NORMAL = "\u001B[34m"; // Blue

    // Colour for warning CLI output
    public static final String CLI_COLOUR_WARNING = "\u001B[33m"; // Yellow

    // Colour for error CLI output
    public static final String CLI_COLOUR_ERROR = "\u001B[31m"; // Red

    // Colour for CLI input
    public static final String CLI_COLOUR_INPUT = "\u001B[0m"; // Reset
}
