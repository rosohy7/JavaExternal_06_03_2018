public class SwimArray {
    ISwim[] swimmers;
    public SwimArray(Object[] candidates)
    {
        int count =0;
        for(int i=0;i<candidates.length;++i)
        {
            if(candidates[i] instanceof ISwim)
                count++;
        }
        swimmers=new ISwim[count];
        count=0;
        for(int i=0;i<candidates.length;++i)
        {
            if(candidates[i] instanceof ISwim)
                swimmers[count++]=(ISwim)candidates[i];
        }
    }
    public void sortSpeed()
    {
        for (int i = swimmers.length - 1; i > 0; --i)
            for (int k = 0; k < i; ++k) {
                if (swimmers[k].maxSwimSpeed() > swimmers[k+1].maxSwimSpeed()) {
                    ISwim t=swimmers[k];
                    swimmers[k]=swimmers[k+1];
                    swimmers[k+1]=t;
                }
            }
    }
    public void print() {
        System.out.println("Content of SwimArray");
        if (swimmers.length == 0) System.out.println("No vehicles to show");
        for (int i = 0; i < swimmers.length; ++i) {
            System.out.println(i + 1 + ". " + swimmers[i]);
        }
        System.out.println();
    }
    public ISwim getFastest() {
        if (swimmers.length == 0) return null;
        ISwim res = swimmers[0];
        for (int i = 0; i < swimmers.length; ++i) {
            if (swimmers[i].maxSwimSpeed() > res.maxSwimSpeed()) res = swimmers[i];
        }

        return res;
    }
    public ISwim getSlowest() {
        if (swimmers.length == 0) return null;
        ISwim res = swimmers[0];
        for (int i = 0; i < swimmers.length; ++i) {
            if (swimmers[i].maxSwimSpeed() < res.maxSwimSpeed()) res = swimmers[i];
        }

        return res;
    }
}
