package external.letiuka;

import external.letiuka.input.TxtReader;
import external.letiuka.logic.BookParser;
import external.letiuka.logic.TextLib;
import external.letiuka.model.Text;

import java.io.IOException;

public class Main
{
    public static void main( String[] args ){
        try {
            TxtReader reader = new TxtReader("FortranBook.txt");
            BookParser parser = new BookParser(reader);
            Text text = parser.parse();
            System.out.println(text);
            System.out.println(TextLib.sortWordsByLetterCount(text,'u'));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
