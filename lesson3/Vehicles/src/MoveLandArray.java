public class MoveLandArray {
    IMoveOnLand[] landGoers;
    public MoveLandArray(Object[] candidates)
    {
        int count =0;
        for(int i=0;i<candidates.length;++i)
        {
            if(candidates[i] instanceof IMoveOnLand)
                count++;
        }
        landGoers =new IMoveOnLand[count];
        count=0;
        for(int i=0;i<candidates.length;++i)
        {
            if(candidates[i] instanceof IMoveOnLand)
                landGoers[count++]=(IMoveOnLand)candidates[i];
        }
    }
    public void sortSpeed()
    {
        for (int i = landGoers.length - 1; i > 0; --i)
            for (int k = 0; k < i; ++k) {
                if (landGoers[k].maxMoveOnLandSpeed() > landGoers[k+1].maxMoveOnLandSpeed()) {
                    IMoveOnLand t= landGoers[k];
                    landGoers[k]= landGoers[k+1];
                    landGoers[k+1]=t;
                }
            }
    }
    public void print() {
        System.out.println("Content of MoveLandArray");
        if (landGoers.length == 0) System.out.println("No vehicles to show");
        for (int i = 0; i < landGoers.length; ++i) {
            System.out.println(i + 1 + ". " + landGoers[i]);
        }
        System.out.println();
    }
    public IMoveOnLand getFastest() {
        if (landGoers.length == 0) return null;
        IMoveOnLand res = landGoers[0];
        for (int i = 0; i < landGoers.length; ++i) {
            if (landGoers[i].maxMoveOnLandSpeed() > res.maxMoveOnLandSpeed()) res = landGoers[i];
        }

        return res;
    }
    public IMoveOnLand getSlowest() {
        if (landGoers.length == 0) return null;
        IMoveOnLand res =landGoers[0];
        for (int i = 0; i < landGoers.length; ++i) {
            if (landGoers[i].maxMoveOnLandSpeed() < res.maxMoveOnLandSpeed()) res = landGoers[i];
        }

        return res;
    }
}
