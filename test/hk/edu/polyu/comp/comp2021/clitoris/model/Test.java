package hk.edu.polyu.comp.comp2021.clitoris.model;

import hk.edu.polyu.comp.comp2021.clitoris.model.shapes.Circle;
import hk.edu.polyu.comp.comp2021.clitoris.model.shapes.Group;
import hk.edu.polyu.comp.comp2021.clitoris.model.shapes.Shape;
import hk.edu.polyu.comp.comp2021.clitoris.model.shapes.Square;

import java.util.*;

// FIXME: This is not a proper unit test. Please remember to remove this file.
// LONG:
// This file denotes a simple test code where two shapes are created, then grouped.
// The list of the group is printed out.
// This is too simple of a proof-of-concept; DO NOT copy this.

public class Test {
    public static void main(String[] args) {
        try {
            ArrayList <Shape> temp   = new ArrayList <>();
            Shape             shape = new Square("SQUARE", 1, 2, 3);
            temp.add(shape);
            shape = new Circle("CIRCLE", 5,6,7);
            temp.add(shape);
            Group group = new Group("GROUP", temp);
            ArrayList<String> out = group.list();
            for (String line : out) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
