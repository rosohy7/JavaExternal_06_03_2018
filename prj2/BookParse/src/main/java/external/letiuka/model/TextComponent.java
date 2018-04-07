package external.letiuka.model;

public abstract class TextComponent {
    protected TextComponent parent;

    public TextComponent getParent() {
        return parent;
    }

    abstract public TextComponent addChar(char chr, boolean isSign); // return pointer to active structure

    public TextComponent(TextComponent parent) {
        this.parent = parent;
    }


}
