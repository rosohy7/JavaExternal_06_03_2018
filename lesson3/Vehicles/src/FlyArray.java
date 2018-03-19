public class FlyArray {
    IFly[] fliers;
    public FlyArray(Object[] candidates)
    {
        int count =0;
        for(int i=0;i<candidates.length;++i)
        {
            if(candidates[i] instanceof IFly)
                count++;
        }
        fliers=new IFly[count];
        count=0;
        for(int i=0;i<candidates.length;++i)
        {
            if(candidates[i] instanceof IFly)
                fliers[count++]=(IFly)candidates[i];
        }
    }
    public void sortSpeed()
    {
        for (int i = fliers.length - 1; i > 0; --i)
            for (int k = 0; k < i; ++k) {
                if (fliers[k].maxFlightSpeed() > fliers[k+1].maxFlightSpeed()) {
                    IFly t=fliers[k];
                    fliers[k]=fliers[k+1];
                    fliers[k+1]=t;
                }
            }
    }
    public void print() {
        System.out.println("Content of FlyArray");
        if (fliers.length == 0) System.out.println("No vehicles to show");
        for (int i = 0; i < fliers.length; ++i) {
            System.out.println(i + 1 + ". " + fliers[i]);
        }
        System.out.println();
    }
    public IFly getFastest() {
        if (fliers.length == 0) return null;
        IFly res = fliers[0];
        for (int i = 0; i < fliers.length; ++i) {
            if (fliers[i].maxFlightSpeed() > res.maxFlightSpeed()) res = fliers[i];
        }

        return res;
    }
    public IFly getSlowest() {
        if (fliers.length == 0) return null;
        IFly res = fliers[0];
        for (int i = 0; i < fliers.length; ++i) {
            if (fliers[i].maxFlightSpeed() < res.maxFlightSpeed()) res = fliers[i];
        }

        return res;
    }
}
