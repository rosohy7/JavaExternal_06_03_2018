package external.letiuka.serialization;

import external.letiuka.model.CoffeeTruck;

import java.io.*;

public class TruckIO {
    public CoffeeTruck readTruck(String file)
            throws IOException, ClassNotFoundException {
        CoffeeTruck truck = null;
        ObjectInputStream objstr = null;
        try {
            objstr = new ObjectInputStream(new FileInputStream(
                    "src" + File.separator + "main" +
                            File.separator + "Resources" + File.separator + file));
            truck = (CoffeeTruck) objstr.readObject();
        } finally {
            if (objstr != null)
                objstr.close();
        }
        return truck;
    }

    public void writeTruck(CoffeeTruck truck, String file)
            throws IOException {
        ObjectOutputStream objstr = null;
        try {
            objstr = new ObjectOutputStream(new FileOutputStream(
                    "src" + File.separator + "main" +
                            File.separator + "Resources" + File.separator + file, false));
            objstr.writeObject(truck);
        } finally {
            if (objstr != null)
                objstr.close();
        }
    }
}
