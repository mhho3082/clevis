package hk.edu.polyu.comp.comp2021.clitoris.model;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        try {
            ArrayList <Shape> temp   = new ArrayList <>();
            Shape             shapey = new Square("Yo", 1, 2, 3);
            temp.add(shapey);
            shapey = new Circle("Hi", 5,6,7);
            temp.add(shapey);
            Group group = new Group("groupy", temp);
            ArrayList<String> out = group.list();
            for (String line : out) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
