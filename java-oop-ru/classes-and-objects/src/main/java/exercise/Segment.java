package exercise;

// BEGIN
public final class Segment {
    private final Point beginPoint;
    private final Point endPoint;

    public Segment(final Point beginPoint, final Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getMidPoint() {
        final int middleDivider = 2;
        return new Point((beginPoint.getX() + endPoint.getX()) / middleDivider,
                (beginPoint.getY() + endPoint.getY()) / middleDivider);
    }
}
// END
