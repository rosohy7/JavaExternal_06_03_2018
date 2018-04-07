package external.letiuka.model;

import java.util.ArrayList;
import java.util.List;

public class Word extends TextComponent implements SentenceComponent {
    List<WordChar> wordChars;
    public Word(TextComponent parent) {
        super(parent);
        wordChars =new ArrayList<WordChar>();
    }
    @Override
    public TextComponent addChar(char chr, boolean isSign) {
        if(!isSign)
            wordChars.add(new WordChar(chr,this));
        else throw new IllegalArgumentException();
        return this;
    }

    @Override
    public String toString() {
        StringBuilder str=new StringBuilder();
        for(WordChar chr : wordChars) {
                str.append(chr);
        }
        return str.toString();
    }

    public List<WordChar> getWordChars() {
        return wordChars;
    }
}
