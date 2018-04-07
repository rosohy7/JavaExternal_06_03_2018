package external.letiuka.model;

public class WordChar extends TextComponent {
    char chr;
    public WordChar(char chr,TextComponent parent) {
        super(parent);
        this.chr=chr;
    }
    @Override
    public TextComponent addChar(char chr, boolean isSign) {
        System.err.println("Warning: using addChar() on WordChar");
        if(!isSign)
            this.chr=chr;
        else throw new IllegalArgumentException();
        return this;
    }
    @Override
    public String toString() {
        return chr+"";
    }

    public char getChr() {
        return chr;
    }
}
