package external.letiuka;

import java.io.Serializable;

public abstract class Coffee implements Cloneable, Serializable {
    static long serialVersionUID = 1;
    public static final int EMPTY = 0;
    public static final int ARABICA = 1;
    public static final int CANEPHORA = 2;
    public static final int LIBERICA = 3;
    public static final int DEWEVREI = 4;

    int type;                     // Coffee.ARABICA ...
    int quality;                  // 1-100

    public Coffee() {

    }

    public Coffee(int type, int quality) {
        if (type < 0 || type > 4 ||
                (type == 0) && quality != -1) throw new IllegalArgumentException();
        this.type = type;
        if (type != 0 && (quality < 1 || quality > 100)) throw new IllegalArgumentException();
        this.quality = quality;
    }

    public int getType() {
        return type;
    }

    public int getQuality() {
        return quality;
    }

    @Override
    public abstract Coffee clone();

    public String typeToString() {
        switch (type) {
            case 0:
                return "no type";
            case 1:
                return "arabica";
            case 2:
                return "canephora";
            case 3:
                return "liberica";
            case 4:
                return "dewevrei";
            default:
                return "unknown type";
        }
    }
}
