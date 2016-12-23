package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class EgyptTests {

    @Test

    public void testArea() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 3);
        Point p3 = new Point(4, 0);
        Assert.assertEquals(p1.distance(p2), 3.0);
        Assert.assertEquals(p2.distance(p3), 5.0);
        Assert.assertEquals(p3.distance(p1), 4.0);
    }

}