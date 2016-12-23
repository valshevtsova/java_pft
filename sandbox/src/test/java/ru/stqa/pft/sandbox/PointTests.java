package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTests {

    @Test

    public void testArea() {
        Point p1 = new Point(0, 12);
        Point p2 = new Point(5, 0);
        Assert.assertEquals(p1.distance(p2), 13.0);
    }

}