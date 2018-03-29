import javax.swing.*;
import java.awt.*;

public class PolygonCanvas extends JPanel {
    FigureArray figs;
    PolygonCanvas()
    {
        figs=new FigureArray(0);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int i=0;i<figs.getLen();++i)
        {
            if(figs.getFigure(i)!=null) paintFigure(g,figs.getFigure(i));

        }

    }
    public void addFigure(Figure newfig)
    {
        figs.addFigure(newfig);
        repaint();
    }
    public void paintFigure(Graphics g, Figure figure)
    {
        if(figure instanceof Polygon)
        {
            Polygon poly=(Polygon) figure;
            for(int i=0;i<poly.getSideCount();++i)
            {
                paintFigure(g,poly.getSide(i));
            }
        }
        else if(figure instanceof Line)
            drawLine(g,(Line)figure);
        else if(figure instanceof Point)
            drawPoint(g,(Point)figure);
    }
    public void drawLine(Graphics g, Line line)
    {
        if(line instanceof ColorLine)
            g.setColor(((ColorLine)line).getColor());
        g.drawLine((int)line.getBeg().getX(),(int)line.getBeg().getY(),(int)line.getEnd().getX(),(int)line.getEnd().getY());
        g.setColor(Color.BLACK);
        drawPoint(g,line.getBeg());
        drawPoint(g,line.getEnd());
    }
    public void drawPoint(Graphics g, Point point)
    {
        if(point instanceof ColorPoint)
            g.setColor(((ColorPoint)point).getColor());

        g.fillOval((int)point.getX()-2,(int)point.getY()-2,4,4);
        g.setColor(Color.BLACK);
    }

}
