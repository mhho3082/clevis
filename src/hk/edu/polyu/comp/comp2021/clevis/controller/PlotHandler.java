package hk.edu.polyu.comp.comp2021.clevis.controller;

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
    private ArrayList<double[]> originalPlotList;
    private int zoom;
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

        this.zoom = 0;
        this.dx = 0;
        this.dy = 0;
    }

    /**
     * Sets the center of the plotting algorithm.
     *
     * @param width  the width of the plotting region
     * @param height the height of the plotting region
     * @param insets the borders of the plotting region
     */
    public void setCenter(int width, int height, Insets insets) {
        centerX = (width - insets.left) / 2;
        centerY = (height - insets.bottom) / 2;
    }

    /**
     * Updates the plot after mouse drags.
     *
     * @param dx The change on x-coordinates made by mouse
     * @param dy The change on y-coordinates made by mouse
     */
    public void dragUpdate(int dx, int dy) {
        this.dx += dx;
        this.dy += dy;

        // TODO: Change data appropriately
        inToOut();
    }

    // TODO: Handle zoom

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
        outPlotList.clear();
        double[] tempOut;

        for (double[] tempIn : originalPlotList) {
            // TODO: Handle zoom

            if (tempIn[4] == 0) {
                tempOut = tempIn.clone();
                tempOut[0] += this.dx + this.centerX;
                tempOut[1] += this.dy + this.centerY;
                tempOut[2] += this.dx + this.centerX;
                tempOut[3] += this.dy + this.centerY;
            } else {
                tempOut = tempIn.clone();
                tempOut[0] += this.dx + this.centerX;
                tempOut[1] += this.dy + this.centerY;
            }

            outPlotList.add(tempOut);
        }
    }
}
