package external.letiuka.util;

import java.io.Serializable;
import java.util.Arrays;

public class ArrayList implements Serializable {
    static long serialVersionUID = 1;
    private Object[] data;
    private int maxLen = 16;
    private int len = 0;

    public ArrayList() {
        data = new Object[maxLen];
    }

    public void sort() {
        if(len>1)
            Arrays.sort(data,0,len);
    }

    public int getLen() {
        return len;
    }

    public void reset() {
        len = 0;
    }

    public Object get(int index) {
        return (index >= len) ? null : data[index];
    }

    public void set(int index, Object val) {
        if (index < len)
            data[index] = val;
    }

    public void add(Object val) {
        if (val != null) {
            resize(len + 1);
            set(len-1, val);
        }
    }

    public boolean remove(int index) {
        if (index < len) {
            for (int i = index; i < len - 1; ++i) {
                data[i] = data[i + 1];
            }
            len--;
            return true;
        }
        return false;


    }

    private void resize(int newLen) {
        Object[] newData;
        if (newLen > maxLen) {
            while (newLen > maxLen) {
                maxLen *= 2;
            }
            newData = new Object[maxLen];
            for (int i = 0; i < len; ++i) {
                newData[i] = data[i];
            }
            data = newData;
        }
        len = newLen;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("");
        if(len==0) res.append("There is nothing inside");
        for (int i = 0; i < len; ++i) {
            res.append(i + ". " +
                    data[i].toString() + "\n");
        }
        return res.toString();
    }
}
