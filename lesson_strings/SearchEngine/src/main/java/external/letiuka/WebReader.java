package external.letiuka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebReader {
    public String readString(String URLname) throws IOException {
        URL website = new URL(URLname);
        URLConnection connection = website.openConnection();
        BufferedReader bf=null;
        StringBuilder sb;
        try {
            bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String str;
            sb = new StringBuilder();
            while ((str = bf.readLine()) != null)
                sb.append(str);
        } finally {
            if (bf != null) bf.close();
        }
        return sb.toString();
    }
}
