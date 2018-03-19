import java.lang.String;

class Rename{

    private String pencil;
    private String tabletNamr;

    public void touchDevice(int x, int y, float strength, Device device){
        device.touchByStylus(x, y, strength);
    }

    public boolean checkIPad(){
        if(tabletNamr.equals("iPad")) return true;
        return false;
    }

    public String getPencil() {
        return pencil;
    }

    interface Device{
        void touchByStylus(int x, int y, float strength);
    }
}