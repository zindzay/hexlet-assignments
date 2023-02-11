package exercise;

// BEGIN
public final class Cottage implements Home {
    private final double area;
    private final int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home another) {
        return Double.compare(getArea(), another.getArea());
    }

    @Override
    public String toString() {
        return String.format("%d этажный коттедж площадью %s метров", floorCount, getArea());
    }
}
// END
