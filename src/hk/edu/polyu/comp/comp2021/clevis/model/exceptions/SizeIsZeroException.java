package hk.edu.polyu.comp.comp2021.clevis.model.exceptions;

/**
 * Warns of a zero-sized shape.
 * The warning is done at base shapes,
 * and user shapes are thrown this exception at and passes it on.
 *
 * @author Ho Man Hin
 */
public class SizeIsZeroException extends Exception {
}
