package hk.edu.polyu.comp.comp2021.clevis.controller;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;

import java.awt.*;
import java.util.ArrayList;

public class PlotHandler {
    //   Line: x1, x2, y1, y2, 0
    // Circle: xLeft, yTop, w, h, 1
    private final Clevis clevis;
    private ArrayList<double[]> originalPlotList;
    private final ArrayList<double[]> outPlotList;

    private int zoom;
    private int dx;
    private int dy;
    private int centerX;
    private int centerY;

    public PlotHandler(Clevis clevis) {
        this.clevis = clevis;
        this.originalPlotList = new ArrayList<>();
        this.outPlotList = new ArrayList<>();

        this.zoom = 0;
        this.dx = 0;
        this.dy = 0;
    }

    public void setCenter(int width, int height, Insets insets) {
        centerX = (width - insets.left) / 2;
        centerY = (height - insets.bottom) / 2;
    }

    public void dragUpdate(int dx, int dy) {
        this.dx += dx;
        this.dy += dy;

        // TODO: Change data
        inToOut();
    }

    // TODO: Handle zoom

    public void commandUpdate() {
        this.originalPlotList = clevis.getPlot();

        inToOut();
    }

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
                outPlotList.add(tempOut);
            } else {
                tempOut = tempIn.clone();
                tempOut[0] += this.dx + this.centerX;
                tempOut[1] += this.dy + this.centerY;
                outPlotList.add(tempOut);
            }
        }
    }
}
