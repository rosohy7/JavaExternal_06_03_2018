import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

class Figure  implements Serializable {

    static long serialVersionUID=1;
    @Override
    public String toString() {
        return "figure";
    }
}

class Point extends Figure  implements Serializable {
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

class ColorPoint extends Point implements Serializable {
    private Color color;

    public ColorPoint(double x, double y, Color color) {
        super(x, y);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return getColor() + " " + super.toString();
    }
}

class Line extends Figure implements Serializable {
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

class ColorLine extends Line implements Serializable {
    private Color color;

    public ColorLine(Point beg, Point end, Color color) {
        super(beg, end);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return getColor() + " " + super.toString();
    }
}

class Polygon extends Figure implements Serializable {
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
    public int getSideCount()
    {
       return sides.length;
    }
    public Line getSide(int index) {
        if (sides[index] != null) return sides[index];
        else {
            int index2 = (index + 1) % sides.length;
            int inheritColor=(int)(Math.random()*2);
            Point father= (inheritColor<1) ? edges[index] : edges[index2];
            if(father instanceof ColorPoint)
            return sides[index] = new ColorLine(edges[index], edges[index2],((ColorPoint)father).getColor());
            else return sides[index] = new Line(edges[index], edges[index2]);
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

class FigureArray {
    private int maxLen = 16;
    private int len;
    public Figure[] data;

    public FigureArray(int len) {
        this.len = len;
        while (maxLen < len) maxLen *= 2;
        this.data = new Figure[maxLen];

    }

    public int getLen() {
        return len;
    }

    public Figure getFigure(int index) {
        if (index >= len)
            return null;
        return data[index];
    }

    public void setFigure(int index, Figure fig) {
        if (index >= len)
            resize(index + 1);
        data[index] = fig;
    }
    public void addFigure(Figure fig)
    {
        setFigure(len,fig);
    }

    private void resize(int newLen) {
        len = newLen;
        if (len > maxLen) {
            while (maxLen < len)
                maxLen *= 2;
            Figure[] newData = new Figure[maxLen];
            for (int i = 0; i < data.length; ++i)
                newData[i] = data[i];
            data = newData;
        }
    }

}


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        PolygonDrawer pol=new PolygonDrawer();


       // pol.addFigure(triangle);
        /*FigureArray figures = new FigureArray(1);
        figures.setFigure(0, triangle);
        figures.setFigure(1, points[1]);
        figures.setFigure(2, new ColorLine(points[0], new Point(4, 0), "red"));
        figures.setFigure(3, new Point(5, 7));
        for (int i = 0; i < figures.getLen(); ++i) {
            Figure t = figures.getFigure(i);
            if (t != null) System.out.println(t);
        }*/

        FigureIO figIO=new FigureIO();/*
        Point[] points = {new Point(100, 100), new ColorPoint(300, 400, Color.red), new Point(100, 400)};
        Figure triangle = new Polygon(points);
        pol.addFigure(triangle);


        points= new Point[]{new ColorPoint(600, 5, Color.green),
                new ColorPoint(650, 40, Color.green),
                new ColorPoint(610, 35, Color.green),
                new ColorPoint(700, 100, Color.green),
                new ColorPoint(610, 95, Color.green),
                new ColorPoint(610, 150, Color.lightGray),
                new ColorPoint(590, 150, Color.lightGray),
                new ColorPoint(590, 95, Color.green),
                new ColorPoint(500, 100, Color.green),
                new ColorPoint(590, 35, Color.green),
                new ColorPoint(550, 40, Color.green)};
        Polygon tree=new Polygon(points);
        pol.addFigure(tree);

        points= new Point[]{new ColorPoint(600, 200, Color.green),
                new ColorPoint(650, 300, Color.yellow),
                new ColorPoint(520, 220, Color.pink),
                new ColorPoint(680, 220, Color.blue),
                new ColorPoint(550, 300, Color.red),};
        Polygon star = new  Polygon(points);
        pol.addFigure(star);

        Thread.sleep(2000);
        figIO.saveFigure(triangle,FigureIO.TRIANGLE);
        figIO.saveFigure(tree,FigureIO.TREE);
        figIO.saveFigure(star,FigureIO.STAR);*/
        Polygon triangle = (Polygon)figIO.loadFigure(FigureIO.TRIANGLE);
        pol.addFigure(triangle);
        Polygon tree = (Polygon)figIO.loadFigure(FigureIO.TREE);
        pol.addFigure(tree);
        Polygon star = (Polygon)figIO.loadFigure(FigureIO.STAR);
        pol.addFigure(star);
    }
}
