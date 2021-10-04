package hk.edu.polyu.comp.comp2021.clitoris.model.newShapes;

import java.math.BigDecimal;

public class Segment {
    private final Point point1;
    private final Point point2;

    public Segment(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public void move(BigDecimal dx, BigDecimal dy) {
        point1.move(dx, dy);
        point2.move(dx, dy);
    }

    public boolean equals(Segment basicLine) {
        if (this.point1.equals(basicLine.point1) && this.point2.equals((basicLine.point2))) {
            return true;
        } else return this.point1.equals(basicLine.point2) && this.point2.equals((basicLine.point1));
    }

    public BigDecimal getLength() {
        return this.point1.getLength(point2);
    }

    public boolean isOnSegment(Point point) {
        // Is exactly on segment

        BigDecimal tempA = this.point1.getLength(point);
        BigDecimal tempB = this.point2.getLength(point);
        return tempA.add(tempB).equals(this.getLength());
    }

    public boolean isContains(Point point) {
        // Compares < 0.05
        BigDecimal length1 = this.point1.getLength(point);
        BigDecimal length2 = this.point2.getLength(point);

        // If out of reach
        if (
                length1.compareTo(this.getLength()) > 0
                        && length2.doubleValue() < 0.05
        ) {
            return true;
        } else if (
                length2.compareTo(this.getLength()) > 0
                        && length1.doubleValue() < 0.05
        ) {
            return true;
        } else if (
                perpendicularDistance(point).doubleValue() < 0.05
        ) {
            return true;
        }
        return false;
    }

    public BigDecimal perpendicularDistance(Point point) {
        double lengthA = this.point1.getLength(point).doubleValue();
        double lengthB = this.point2.getLength(point).doubleValue();
        double lengthAll = this.getLength().doubleValue();

        double s = (lengthA + lengthB + lengthAll) / 2.0;

        double out = 2.0 * Math.sqrt(s * (s - lengthA) * (s - lengthB) * (s - lengthAll)) / lengthAll;

        return new BigDecimal(String.valueOf(out));
    }

    public BigDecimal[] boundingBox() {
        // Return [xLeft, yTop, width, height]
        BigDecimal[] temp = new BigDecimal[4];

        temp[0] = point1.getX().min(point2.getX());
        temp[1] = point1.getY().min(point2.getY());
        temp[2] = point1.getX().subtract(point2.getX()).abs();
        temp[3] = point1.getY().subtract(point2.getY()).abs();

        return temp;
    }
}
