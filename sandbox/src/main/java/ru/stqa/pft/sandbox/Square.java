package ru.stqa.pft.sandbox;

/**
 * Created by lerkon on 17.12.2016.
 */
public class Square {

    public double l;

    public Square (double l) {
        this.l = l;
    }

    public double area() {
        return this.l * this.l;
    }
}
