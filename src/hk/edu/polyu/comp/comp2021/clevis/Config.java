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
     * Icon for waiting input
     */
    public static final String CLI_WAIT_ICON = "    ... ";

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
    // Known not to properly work in cmd
    // Seems to work in PowerShell and PowerShell Core
    // Will work in VS Code and Intellij IDEA
    // However, Intellij IDEA handles user inputs as green no matter what,
    //   and cannot be changed by the program

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
    public static final Dimension GUI_MAIN_FRAME_SIZE = new Dimension(600, 650);

    /**
     * Minimum dimensions of GUI main frame; cannot resize smaller than this
     */
    public static final Dimension GUI_MAIN_FRAME_MIN_SIZE = new Dimension(300, 300);

    /**
     * Dimensions of GUI dialog scroll pane
     */
    public static final Dimension GUI_DIALOG_SCROLL_PANE_DIMENSION = new Dimension(500, 200);

    /**
     * Font of GUI dialog output
     */
    public static final Font GUI_DIALOG_FONT = new Font("Courier New", Font.PLAIN, 12);

    /**
     * The value used to reduce the scroll wheel movement
     */
    public static final int GUI_SCROLL_REDUCTION = 100;

    /**
     * The ratio this:1 = plot:ruler for horizontal (weight x) (affects the vertical ruler's size)
     */
    public static final double GUI_RATIO_HORIZONTAL = 12.0;

    /**
     * The ratio this:1 = plot:ruler for vertical (weight y) (affects the horizontal ruler's size)
     */
    public static final double GUI_RATIO_VERTICAL = 12.0;

    /**
     * The maximum amount of separation between two major marks of a ruler (the horizontal one)
     */
    public static final int GUI_RULER_HORIZONTAL_MAJOR_MARKS_MAX_SEPARATION = 75;

    /**
     * The maximum amount of separation between two major marks of a ruler (the vertical one)
     */
    public static final int GUI_RULER_VERTICAL_MAJOR_MARKS_MAX_SEPARATION = 75;

    /**
     * The amount of pixels the lines of the horizontal ruler is pushed down for visibility
     */
    public static final int GUI_RULER_HORIZONTAL_LINE_OFFSET = 3;

    /**
     * The amount of pixels the vertical ruler is pushed right for visibility
     */
    public static final int GUI_RULER_VERTICAL_OFFSET = 3;

    /**
     * The preset zoom level
     */
    public static final double GUI_BASE_ZOOM = 1;

    /**
     * For reducing the scale to 2-something; for ruler
     */
    public static final double GUI_RULER_REDUCE_2 = 2.5;

    /**
     * For reducing the scale to 1-something or 5-something; for ruler
     */
    public static final double GUI_RULER_REDUCE_OTHER = 2.0;

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
