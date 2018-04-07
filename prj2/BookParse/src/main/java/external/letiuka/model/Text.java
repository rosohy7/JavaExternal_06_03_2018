package external.letiuka.model;

import java.util.ArrayList;
import java.util.List;

public class Text extends TextComponent {
    List<Sentence> sentences;

    public Text(TextComponent parent) {
        super(parent);
        sentences = new ArrayList<Sentence>();
    }

    public Text() {
        this(null);
    }

    @Override
    public TextComponent addChar(char chr, boolean isSign) {
        Sentence sen = new Sentence(this);
        sentences.add(sen);
        return sen.addChar(chr, isSign);
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Sentence sen : sentences) {
            str.append(sen);
        }
        return str.toString();
    }

    public List<Sentence> getSentences() {
        return sentences;
    }
}
