public class VehicleFactory {
    public VehicleFactory() {
    }

    public Vehicle createRandVehicle() {
        return createVehicle((int)(Math.random()*15.0),(long)(Math.random()*10000+1000),
                (int)(Math.random()*19+2000),(int)(Math.random()*150+100));
    }

    public Vehicle createVehicle(int code, long price, int year, int maxSpeed) {
        if(code<0) return null;
        else if(code<3) return new Car(price,year,maxSpeed);
        else if(code<5) return new AmphibiousCar(price,year,maxSpeed);
        else if(code<6) return new Batmobile(price,year,maxSpeed);
        else if(code<10) return new Plane(price,year,maxSpeed*10,(int)(Math.random()*100+5));
        else if(code<15) return new Ship(price,year,maxSpeed/2,(int)(Math.random()*100+5),"Port #"+(int)(Math.random()*9+1));
        else return null;
    }

}
