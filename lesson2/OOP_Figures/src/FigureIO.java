import java.io.*;

public class FigureIO  {
    public static String TRIANGLE="Triangle.ser";
    public static String TREE="Tree.ser";
    public static String STAR="Star.ser";
    public void saveFigure (Figure fig, String filename) throws IOException
    {
        ObjectOutputStream objstr=null;
        try
        {
            objstr=new ObjectOutputStream(new FileOutputStream(filename,false));
            objstr.writeObject(fig);
        }
        finally
        {
            if(objstr!=null) objstr.close();

        }

    }
    public Figure loadFigure(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream objstr=null;
        try
        {
            objstr=new ObjectInputStream(new FileInputStream(filename));
             return (Figure)objstr.readObject();
        }
        finally
        {
            if(objstr!=null) objstr.close();


        }

    }
}
