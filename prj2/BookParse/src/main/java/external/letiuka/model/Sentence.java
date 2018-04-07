package external.letiuka.model;

import java.util.ArrayList;
import java.util.List;

public class Sentence extends TextComponent {
    List<SentenceComponent> components;

    public Sentence(TextComponent parent) {
        super(parent);
        components = new ArrayList<SentenceComponent>();
    }

    @Override
    public TextComponent addChar(char chr, boolean isSign) {
        SentenceComponent comp;
        if (isSign) {
            comp = new Sign(chr, this);
            components.add(comp);
            return this;
        } else {
            comp = new Word(this);
            components.add(comp);
            return ((Word) comp).addChar(chr, false);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        String separator="";
        for (SentenceComponent comp : components) {
            if (comp instanceof Sign) str.append(comp);
            else str.append(separator + comp);
            separator=" ";
        }
        str.append("\n");
        return str.toString();
    }

    public List<SentenceComponent> getComponents() {
        return components;
    }
}
