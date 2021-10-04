package hk.edu.polyu.comp.comp2021.clitoris.model.newShapes;

import java.math.BigDecimal;

public class Point {
    private BigDecimal x;
    private BigDecimal y;

    public Point (double x, double y){
        this.x = new BigDecimal(String.valueOf(x));
        this.y = new BigDecimal(String.valueOf(y));
    }

    public Point (BigDecimal x, BigDecimal y){
        this.x = new BigDecimal(String.valueOf(x));
        this.y = new BigDecimal(String.valueOf(y));
    }

    public void move(BigDecimal dx, BigDecimal dy) {
        this.x = this.x.add(dx);
        this.y = this.y.add(dy);
    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    public boolean equals(Point point) {
        return this.x.equals(point.x) && this.y.equals(point.y);
    }

    public BigDecimal getLength(Point point) {
        double temp = Math.sqrt(
                Math.pow(this.x.doubleValue() - point.x.doubleValue(), 2)
                        + Math.pow(this.y.doubleValue() - point.y.doubleValue(), 2)
        );
        return new BigDecimal(temp);
    }
}
