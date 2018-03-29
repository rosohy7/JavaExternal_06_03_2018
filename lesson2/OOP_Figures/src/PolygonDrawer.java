import javax.swing.*;
import java.awt.*;

public class PolygonDrawer{
    PolygonCanvas polCan;
    public PolygonDrawer()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Polygon Canvas");

        polCan =new PolygonCanvas();
        frame.add(polCan);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
    }
    public void addFigure(Figure newfig)
    {
        polCan.addFigure(newfig);
    }


}
