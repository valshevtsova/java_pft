package ru.stqa.pft.sandbox;

public class DistanceChecker {

    public static void main (String[] args) {

        Point p1 = new Point(1, 1);
        Point p2 = new Point(2,2);

        System.out.println("Расстояние между точками = " + p1.distance(p2));

    }


}
