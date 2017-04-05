package ru.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void main (String[] args) {
		hello("world");

		Square s = new Square(5);
		System.out.println("Square square " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4, 6);
		System.out.println("Square rectangle " + r.a + " and " + r.b + " = " + r.area());

		Point p1 = new Point(1, 1);
		Point p2 = new Point(2,2);
		System.out.println("Distance " + distance(p1, p2));

	}

	public static void hello(String somebody) {
		System.out.println("Hello" + somebody + "!");
	}

	public static double distance(Point p1, Point p2) {

		return Math.sqrt(((p2.x - p1.x)* (p2.x - p1.x)) + ((p2.y - p1.y)* (p2.y - p1.y)));
	}
}