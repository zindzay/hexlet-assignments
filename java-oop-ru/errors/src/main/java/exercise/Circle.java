package exercise;

// BEGIN
public final class Circle {
    private final Point center;
    private final int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Radius can not be negative");
        }

        return Math.PI * Math.pow(radius, 2);
    }
}
// END
