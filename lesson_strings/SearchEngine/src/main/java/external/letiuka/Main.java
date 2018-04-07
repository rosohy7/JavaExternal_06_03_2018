package external.letiuka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        WebReader read = new WebReader();
        String URLname="https://docs.oracle.com/javase/7/docs/api/java/lang/String.html";
        String URLname2="https://stackoverflow.com/";
        WebCrawler crawler = new WebCrawler(URLname2);
        crawler.scan(20);
        crawler.print();



    }

}