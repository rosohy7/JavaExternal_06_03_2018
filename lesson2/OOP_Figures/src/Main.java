import java.util.List;
import java.util.ArrayList;

class Figure {
    @Override
    public String toString() {
        return "figure";
    }
}

class Point extends Figure {
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "point [ " + getX() + ", " + getY() + " ]";
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

class ColorPoint extends Point {
    private String color;

    public ColorPoint(double x, double y, String color) {
        super(x, y);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return getColor() + " " + super.toString();
    }
}

class Line extends Figure {
    private Point beg, end;

    public Point getBeg() {
        return beg;
    }

    public Point getEnd() {
        return end;
    }

    public Line(Point beg, Point end) {
        this.beg = beg;
        this.end = end;
    }

    @Override
    public String toString() {
        return "line from " + beg.toString() + " to " + end.toString();
    }
}

class ColorLine extends Line {
    private String color;

    public ColorLine(Point beg, Point end, String color) {
        super(beg, end);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return getColor() + " " + super.toString();
    }
}

class Polygon extends Figure {
    private Point[] edges;
    private Line[] sides;

    public Polygon(Point[] edges) {
        this.edges = new Point[Math.max(edges.length, 3)];
        sides = new Line[this.edges.length];

        for (int i = 0; i < edges.length; ++i) {

            this.edges[i] = edges[i];
        }
        for (int i = edges.length; i < this.edges.length; ++i) {

            this.edges[i] = new Point(i, i);
        }
    }

    public Line getSide(int index) {
        if (sides[index] != null) return sides[index];
        else {
            int index2 = (index + 1) % sides.length;
            return sides[index] = new Line(edges[index], edges[index2]);
        }
    }

    public Point getEdge(int index) {
        if (index >= 0 && index < edges.length) return edges[index];
        else return null;
    }

    @Override
    public String toString() {
        String ans = "polygon with edges: ";
        for (int i = 0; i < edges.length - 1; ++i) {

            ans += edges[i].toString() + ", ";
        }
        ans += edges[edges.length - 1].toString() + ".";
        return ans;

    }
}

public class Main {

    public static void main(String[] args) {
        Point[] points = {new Point(1, 1), new ColorPoint(1, 8, "blue"),new Point(2,6)};
        Figure a = new Polygon(points);
        List<Figure> figures = new ArrayList<Figure>();
        figures.add(a);
        figures.add(points[1]);
        figures.add(new ColorLine(points[0],new Point(4,0),"red"));
        figures.add(new Point(5,7));
        for(Figure fig : figures)
            System.out.println(fig);

    }
}
