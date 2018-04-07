package external.letiuka.logic;

import external.letiuka.model.TextComponent;
import external.letiuka.input.BookInput;
import external.letiuka.model.Sentence;
import external.letiuka.model.Text;
import external.letiuka.model.Word;

import java.io.IOException;

public class BookParser {
    BookInput input;
    TextComponent cur;
    String midSignRegex = "[/\\-,:;<>=+*\\\\'\"]";
    String wordCharRegex = "\\w";
    String endSignRegex = "[\\?\\!\\.]+";

    public BookParser(BookInput input) {
        this.input = input;
    }

    String prepareString(String str) {
        String res = str;
        res = res.replaceAll("<.{0,100}?>", " "); // remove tags
        res = res.replaceAll("\\s+", " ");
        res = res.trim();
        res = "^" + res + "$";
        if (res.matches(".*\\w-\\$"))              //word hyphenation( multiple lines)
            res = res.replace("-$", "$H");
        return res;
    }

    public Text parse() {
        Text text= new Text();
        String str;
        TextComponent cur = text;
        try {
            while ((str = input.readln()) != null) {
                if (str == "") continue;
                str = prepareString(str);
                for (int i = 1; i < str.length() - 1; i++) {
                    char chr = str.charAt(i);
                    String chrStr = chr + "";
                    if (chr == ' ') {
                        if (cur instanceof Word) cur = cur.getParent();
                    } else if (chrStr.matches(wordCharRegex)) {
                        cur = cur.addChar(chr, false);
                    } else if (chrStr.matches(midSignRegex)) {
                        if (cur instanceof Word) cur = cur.getParent();
                        cur = cur.addChar(chr, true);
                    } else if (chrStr.matches(endSignRegex)) {
                        if (cur instanceof Word) cur = cur.getParent();
                        cur = cur.addChar(chr, true);
                        if (cur instanceof Sentence) cur = cur.getParent();
                    }

                }
                if (str.charAt(str.length() - 1) != 'H' && cur instanceof Word) cur = cur.getParent();//Hyphenation
            }
        } catch (IOException e) {
            System.err.println("Failed to parse book due to an IO exception");
            e.printStackTrace();
        }
        return text;
    }

}
