package hk.edu.polyu.comp.comp2021.clevis.controller;

import hk.edu.polyu.comp.comp2021.clevis.Config;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;

import java.awt.*;
import java.util.ArrayList;

/**
 * The handler for plotting in GUI.
 *
 * @author Ho Man Hin
 */
public class PlotHandler {
    //   Line: x1, x2, y1, y2, 0
    // Circle: xLeft, yTop, w, h, 1

    private final Clevis clevis;
    private final ArrayList<double[]> outPlotList;
    private final double[] horizontalRulerDimension;
    private final double[] verticalRulerDimension;
    private ArrayList<double[]> originalPlotList;
    private double zoom;
    private int dx;
    private int dy;
    private int centerX;
    private int centerY;

    /**
     * Creates a plot handler.
     *
     * @param clevis the model to be used
     */
    public PlotHandler(Clevis clevis) {
        this.clevis = clevis;
        this.originalPlotList = new ArrayList<>();
        this.outPlotList = new ArrayList<>();

        this.zoom = Config.GUI_BASE_ZOOM;
        this.dx = 0;
        this.dy = 0;

        horizontalRulerDimension = new double[]{0, 0};
        verticalRulerDimension = new double[]{0, 0};
    }

    /**
     * Sets the center of the plotting algorithm.
     * Updates the plot after resizing.
     *
     * @param width  the width of the plotting region
     * @param height the height of the plotting region
     * @param insets the borders of the plotting region
     */
    public void sizeUpdate(int width, int height, Insets insets) {
        centerX = (width - insets.left) / 2;
        centerY = (height - insets.bottom) / 2;

        inToOut();
    }

    /**
     * Updates the plot after mouse drags.
     *
     * @param dx The change on x-coordinates made by mouse
     * @param dy The change on y-coordinates made by mouse
     */
    public void dragUpdate(int dx, int dy) {
        this.dx += (int) ((double) dx / zoom);
        this.dy += (int) ((double) dy / zoom);

        inToOut();
    }

    /**
     * Updates the plot after scroll wheel movement.
     * Also works for touchpad.
     * <p>
     * Scroll only considers the center point of the plot;
     * the mouse position during the scroll does not matter.
     *
     * @param movement the amount of movement in the scroll wheel.
     */
    public void scrollUpdate(int movement) {
        double tempZoom = this.zoom;
        this.zoom -= (double) movement / Config.GUI_SCROLL_REDUCTION * tempZoom;

        inToOut();
    }

    /**
     * Updates the plot after user command.
     */
    public void commandUpdate() {
        this.originalPlotList = clevis.getPlot();

        inToOut();
    }

    /**
     * Generates the true plotting guides.
     *
     * @return the plotting guides for panel to directly plot.
     */
    public ArrayList<double[]> getOutPlotList() {
        return outPlotList;
    }

    private void inToOut() {
        // For rulers
        horizontalRulerDimension[0] = (-1 * centerX) / zoom - dx;
        horizontalRulerDimension[1] = (centerX) / zoom - dx;
        verticalRulerDimension[0] = (-1 * centerY) / zoom - dy;
        verticalRulerDimension[1] = (centerY) / zoom - dy;

        // For the plot
        outPlotList.clear();
        double[] tempOut;

        for (double[] tempIn : originalPlotList) {
            tempOut = tempIn.clone();

            tempOut[0] = (tempOut[0] + this.dx) * zoom + this.centerX;
            tempOut[1] = (tempOut[1] + this.dy) * zoom + this.centerY;

            if (tempIn[4] == 0) {
                tempOut[2] = (tempOut[2] + this.dx) * zoom + this.centerX;
                tempOut[3] = (tempOut[3] + this.dy) * zoom + this.centerY;
            } else {
                tempOut[2] *= zoom;
                tempOut[3] *= zoom;
            }

            outPlotList.add(tempOut);
        }
    }

    /**
     * Returns the horizontal ruler dimension.
     *
     * @return [left, right]
     */
    public double[] getHorizontalRulerDimension() {
        return horizontalRulerDimension;
    }

    /**
     * Returns the vertical ruler dimension.
     *
     * @return [top, bottom]
     */
    public double[] getVerticalRulerDimension() {
        return verticalRulerDimension;
    }
}
