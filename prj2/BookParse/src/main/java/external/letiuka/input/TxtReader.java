package external.letiuka.input;

import java.io.*;

public class TxtReader implements BookInput {
    BufferedReader reader;
    String filename;

    public TxtReader(String filename) throws IOException {
        this.filename = filename;
        reader = new BufferedReader(new FileReader(
                "src" + File.separator + "main" + File.separator + "resources" +
                        File.separator + "books" + File.separator + filename));
    }

    public String readln() throws IOException {
        return reader.readLine();
    }

    public char read() throws IOException {
        return (char) reader.read();

    }

}
