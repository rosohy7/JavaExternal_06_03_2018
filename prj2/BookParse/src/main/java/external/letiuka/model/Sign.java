package external.letiuka.model;

public class Sign extends TextComponent implements SentenceComponent {
    char chr;

    public Sign(char chr, TextComponent parent) {
        super(parent);
        this.chr = chr;
    }
    @Override
    public TextComponent addChar(char chr, boolean isSign) {
        System.err.println("Warning: using addChar() on Sign");
        if(isSign)
            this.chr=chr;
        else throw new IllegalArgumentException();
        return this;
    }
    @Override
    public String toString() {
        return chr+"";
    }

}
