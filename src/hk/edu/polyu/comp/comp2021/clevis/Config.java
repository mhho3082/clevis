package hk.edu.polyu.comp.comp2021.clevis;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The settings for the code to function.
 * May be different for each user's preference.
 * Please manually change for now.
 * Will possibly try to give settings functions later.
 *
 * @author Ho Man Hin
 */
public class Config {
    /**
     * Number of decimal place for rounding for output
     */
    public static final int ROUND_OUTPUT = 2;

    /**
     * Number for BigDecimal rounding
     */
    public static final int ROUND_BIG_DECIMAL = 33;

    /**
     * Number for BigDecimal setScale
     */
    public static final int SCALE_SIZE = 29;

    /**
     * Indentation for list and listAll
     */
    public static final String INDENT = "   |"; // 4 spaces

    /**
     * Index to begin html log with
     */
    public static final int HTML_INDEX_BEGIN = 1;

    /**
     * HTML date & time pattern
     */
    public static final String HTML_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    /**
     * Icon for user input
     */
    public static final String CLI_INPUT_ICON = "clevis> ";

    /**
     * Sentence to announce command completed
     */
    public static final String CLI_AOK = "Done";

    /**
     * Sentence to begin warning message
     */
    public static final String CLI_WARNING_BEGIN = "Warning:";

    /**
     * Sentence to begin error message
     */
    public static final String CLI_ERROR_BEGIN = "Error:";

    // For the ANSI escape code for colours:
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    // Note:
    // Colours may not certainly work for Windows
    // Known not to work on cmd
    // Seems to work on PowerShell and PowerShell Core
    // Will work in VS Code and Intellij IDEA

    /**
     * Colour for normal CLI output
     */
    public static final String CLI_COLOUR_NORMAL = "\u001B[34m"; // Blue

    /**
     * Colour for warning CLI output
     */
    public static final String CLI_COLOUR_WARNING = "\u001B[33m"; // Yellow

    /**
     * Colour for error CLI output
     */
    public static final String CLI_COLOUR_ERROR = "\u001B[31m"; // Red

    /**
     * Colour for CLI input
     */
    public static final String CLI_COLOUR_INPUT = "\u001B[0m"; // Reset

    /**
     * Dimensions of GUI main frame
     */
    public static final Dimension GUI_MAIN_FRAME_DIMENSION = new Dimension(600, 650);

    /**
     * Dimensions of GUI dialog scroll pane
     */
    public static final Dimension GUI_DIALOG_SCROLL_PANE_DIMENSION = new Dimension(500, 200);

    /**
     * Font of GUI dialog output
     */
    public static final Font GUI_DIALOG_FONT = new Font("Courier New", Font.PLAIN, 12);

    /**
     * Rounds data to two decimal places for output.
     *
     * @param input the data to be rounded
     * @return the output data
     */
    public static String roundForOutput(BigDecimal input) {
        return input.setScale(ROUND_OUTPUT, RoundingMode.HALF_UP).toString();
    }
}
